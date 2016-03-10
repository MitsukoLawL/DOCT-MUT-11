<!-- Une présentation de votre travail sous la forme d'un tutoriel a suivre. -->
<!--exemples d'utilisation qui montrent le boulot (comment changer tous les + en - des classes
 dont le nom commence par Pouet : modifier tel fichier de config, ainsi que tel autre fichier
  de config, lancer mon script avec machin comme param, et pouf tu regardes le report)-->
# How To #

1. Déposer ses sources dans <b>spoonProcess/toBeMutated/</b>
2. Déposer ses sources dans <b>mutatedCode/src/main</b>
3. Ajouter son projet en entier dans <b>mutatedCode/</b>
    1. Ajouter ses tests dans <b>mutatedCode/src/test/</b>
    2. Ajouter son fichier <b>POM.xml</b>
    3. Ajouter les fichiers complémentaires

-
## Utilisation interactive : #
Lancer $ ./consoleInterface.sh
![Exemple consoleInterface](https://github.com/MitsukoLawL/DOCT-MUT-11/blob/master/doc/img/consoleInterface.png "Exemple d'utilisation de consoleInterface")

## Utilisation script : #
Ecrire les mutations à executer dans $ ./execution.sh.
Lancer execution.sh![Exemple execution.sh](https://github.com/MitsukoLawL/DOCT-MUT-11/blob/master/doc/img/execution.png "Exemple d'utilisation de ./execution.sh")

#### Opérateurs disponibles #
Les opérateurs disponibles sont <b>Op1, Op9, Op12, Op13, Op16,</b> (la description est dans Specifications.md). <br/>
Les deux opérateurs qui suffiront à tester les erreurs de test ou de compilation :
<br/><b>Op1</b> : Transforme des attributs protected en privé. Cet opérateur permettre de vérifier si les classes filles utilisent de forme correcte les attributs protected de la classe mère. Normalement, si la classe fille utilise bien les attributs hérités, ce mutant sera tué à la compilation (mvn clean compile), car ceux-ci ne seront plus visible à la classe fille.
<br/><b>Op9</b> : Si un attribut est déclaré avec le modificateur static, cet opérateur de mutation propose d’enlever ce mot-là. Cela permettra d’évaluer le comportement du système par rapport aux variables de classes et d’instance. Ce mutant peut être tué soit à la compilation soit à l’exécution des asserts. Dans le première cas, supposez qu’une classe <i>A</i> accède à une variable static <i>s</i> d’une classe <i>B</i> sans encapsulation, comme: <i>B.s</i>; cette ligne dans <i>A</i> renverra une erreur de compilation. Supposons maintenant que la classe <i>A</i> accède à <i>s</i> à partir d'une méthode <i>getS()</i> non static. Cela ne donnera plus d'erreur de compilation, mais devra donner une erreur de test, car, il sera possible que la valeur de s ne sera plus la même dans le mutant. 
<br/><b>Op12</b> : Transforme des opérateurs arithmétique (+,-,*,/) et le remplace par un (+,-,*,/) au hasard.
<br/><b>Op13</b> : Cet opérateur de mutation inverse les opérateurs logiques: le && se devine || et vice-versa. Cet opérateur a un rôle important pour savoir si les expressions logiques sont bien formés, sans inconsistance. Les mutants peuvent avoir soit des résultats avec des valeurs inattendues soit une boucle infinie. Ils seront tués aux tests.
<br/><b>Op16</b> : Cet opérateur de mutation remplace les opérateurs d’incrémentation, 
vers l’opérateur de décrémentation correspondant, en prenant compte des formes préfixée et suffixée. C’est-à-dire, si l’incrémentation est fait comme avec la forme <i>++x</i>, le résultat est <i>--x</i>. Ce mutant est chargé de identifier des inconsistances avec les variables dans les itérations des boucles (qui souvent peut se devenir infinie). Il doit être tué dans les tests d’execution, nous avons ajouté l'annotation timeout afin de gérer les boucles infinies potentielles. 

#### Selecteurs #
Les <b>selecteurs</b> sont des pourcentages. C'est-à-dire que si on applique un sélecteur de <i>s%</i>, celui-ci prendra <i>s%</i> des éléments dans l’ensemble des candidats pouvant être mutés pour cet opérateur. Le choix de ces éléments est aléatoire. 

#### Générer le rapport #
<ul>
<li>Si vous utilisez $ ./consoleInterface.sh <br/>
Entrez "exit" lorsque vous souhaitez arrêter les transformations, cela generera le rapport.</li>
<li>Si vous utilisez $ ./execution.sh <br/>
Le rapport sera généré automatiquement</li>
</ul>
Vous trouverez le rapport dans <b>Report/</b>