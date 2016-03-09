<!-- Une présentation de votre travail sous la forme d'un tutoriel a suivre. -->
<!--exemples d'utilisation qui montrent le boulot (comment changer tous les + en - des classes
 dont le nom commence par Pouet : modifier tel fichier de config, ainsi que tel autre fichier
  de config, lancer mon script avec machin comme param, et pouf tu regardes le report)-->
# How To #

1. Déposer ses sources dans <b>spoonProcess/toBeMutated/</b>
2. Déposer ses sources dans <b>mutatedCode/src</b>
3. Ajouter son projet en entier dans <b>mutatedCode/</b>
    1. Ajouter ses tests dans <b>mutatedCode/test/</b>
    2. Ajouter son fichier <b>POM.xml</b>
    3. Ajouter les fichiers complémentaires

-
## Utilisation interactive : #
Lancer consoleInterface.sh
![Exemple consoleInterface](https://github.com/MitsukoLawL/DOCT-MUT-11/blob/master/doc/img/consoleInterface.png "Exemple d'utilisation de consoleInterface")

## Utilisation script : #
Ecrire les mutations à executer dans ./execution.sh.
Lancer execution.sh![Exemple execution.sh](https://github.com/MitsukoLawL/DOCT-MUT-11/blob/master/doc/img/execution.png "Exemple d'utilisation de ./execution.sh")


Les opérateurs disponibles sont <b>Op1, Op9, Op12, Op13, Op16,</b> (la description est dans Specifications.md). <br/>
Les deux opérateurs qui suffiront à tester les erreurs de test ou de compilation :
<br/><b>Op1</b> : Transforme des attributs protected en privé. Cet opérateur permettre de vérifier si les classes filles utilisent de forme correcte les attributs protecteds de la classe mère. Normalement, si la classe fille utilise bien les attributs hérités, ce mouton sera tué à la compilation (mvn clean compile), car ceux-ci ne seront plus visible à la classe fille.
<br/><b>Op9</b> : Si un attribut est déclaré avec le modificateur static, cet opérateur de mutation propose d’enlever ce mot-là. Cela permettra d’évaluer le comportement du système par rapport aux variables de classes et d’instance. Ce mouton peut être tué soit à la compilation soit à l’exécution des asserts. Dans le première cas, supposez qu’une classe <i>A</i> accède à une variable static <i>s</i> d’une classe <i>B</i> sans encapsulation, comme: <i>B.s</i>; cette linge dans <i>A</i> doit donner un erreur de compilation. Supposez maintenant qui la classe <i>A</i> accède à <i>s</i> à partir une méthode <i>getS()</i> non static. Cela ne donne plus erreur de compilation, mais devra donner un erreur par rapport les tests, car, probablement, na valeur de s sera sera plus la même dans le mouton. 
<br/><b>Op12</b> : Transforme des opérateurs arithmétique -> Bloque au test Unitaire (mvn test).

Les selecteurs sont des pourcentages. C'est-à-dire que si on applique un sélecteur de <i>s%</i>, celui-ci prendra <i>s%</i> des éléments dans l’assemble desquels peuvent souffrir mutations dans un code. Le choix de ces éléments est aleatoire. 

Entrez "exit" lorsque vous souhaitez arrêter les transformations, cela generera le rapport.
Vous trouverez le rapport dans <b>Report/</b>