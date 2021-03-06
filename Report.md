<!-- Une analyse critique de votre travail, quelle est l'architecture (dev et opérationnel) mise en oeuvre dans votre framework, quelles sont ses forces et ses faiblesses, ...  -->


Architecture
========

Représentation de la chaîne de production
![Execution](https://github.com/MitsukoLawL/DOCT-MUT-11/blob/master/doc/img/mutationTesting.png "Dessin représentant une execution")

<b>Le framework est divisé en quatre parties que nous allons décrire ci-dessous</b>

-

#### SpoonProcess/ #
Processus de modification du code dans toBeMutated/
Composé d'un Main.java et de classes d'opérateurs de mutation.

#### mutatedCode/ #
Les sources de toBeMutated/ sont envoyées dans le dossier mutatedCode/src/.
Il représente le projet sur lequel nous allons travailler.
Les tests unitaires à appliquer à notre projet doit être placé dans mutatedCode/test/

#### XML&HTML/ #
Processus qui gère les xml des tests, et qui à la fin de toutes les transformations, génèrera le rapport HTML


#### Report/ #
Dossier où est généré le rapport HTML
Contenant :
<ul>
<li>Le nombre de mutants tués</li>
<li>Le nombre de mutants vivants</li>
<li>Le nombre de mutants morts-nés</li>
</ul>
Pour chaque mutation, un détail dans un tableau représentant
<ul>
<li>Le nombre tests réussis</li>
<li>Le nombre tests ratés</li>
<li>Le nombre tests en erreur</li>
</ul>
De plus, ces chiffres sont accompagnés de deux graphes afin d'illustrer les résultats.

Les outils que nous avons utilisé pour ce rapport son XSL, Bootstrap, Highcharts et JQuery.

-

## Bash #
Pour relier chaque partie du framework, nous utilisons des scripts shell.
<ul>
<li><b>$ ./process.sh</b> [dossier Source] [Operation] [Selecteur]</li>
<br/>Qui permettra de faire tout le process depuis toBeMutated/ à xmlResult/ (cf Dessin)
</li>
<li><b>$ ./consoleInterface</b> <br/>
    Qui demandera le "Dossier Source", "le mutant", "le selecteur" de facon interactive</li>
</ul>
![Exemple consoleInterface](https://github.com/MitsukoLawL/DOCT-MUT-11/blob/master/doc/img/consoleInterface.png "Exemple d'utilisation de consoleInterface")
    <p>Lorsque l'on entrera "exit", cela mettra fin au traitement de mutant et un HTML sera généré (grâce à l'appel de la fonction CreatHTML.java)</p>
<ul><li> <b>$ ./execution.sh</b>
    Où nous ajoutons les lignes <i>./process.sh "Dossier Source" "mutant" "selecteur"</i>
    Pour autant de mutation que nous souhaitons appliquer.</li>
</ul>
![Exemple execution.sh](https://github.com/MitsukoLawL/DOCT-MUT-11/blob/master/doc/img/execution.png "Exemple d'utilisation de ./execution.sh")
    <p>Après le dernier traitement de mutant, un HTML est généré (grâce à l'appel de la fonction CreatHTML.java)</p>
    
#### Forces : #
--------
<ul>
<li>Le processus de transformation est indépendant du projet à tester.</li>
<li>Nous pouvons ajouter des opérateurs supplémentaires assez facilement : Il suffit d'ajouter la nouvelle classe au spoonProcess, et d'ajouter cet opérateur dans la liste du Main.</li>
<li>La chaîne de production gère les erreurs de compilation ainsi que de tests. Les opérateurs disponibles sont capables de générer ces deux types d'erreurs.</li>
<li>Les opérateurs que nous avons choisi d'implémenter. Car ils ne s'attaquent pas seulement à des changements d'opérateurs, mais à des aspects orientés objets tels que l'héritage, et des attributs d'instance</li>
<li>Il est possible de muter de gros projets</li>
</ul>

#### Faiblessses : #

-----------
<ul>
<li>Sources dupliquées</li>
<li>Le programme spoonProcess est peut-être un peu trop gros pour ce qu'il fait</li>
<li>Nous ne pouvons appliquer au code qu'un seul opérateur à la fois</li>
<li>Le choix de selecteur est limité à un percentage</li>
<li>Peu d'opérateurs actuellement, mais facile à intégrer par la suite</li>
<li>Pour que le projet puisse être muté, il faut que les dépendances de son pom.xml soit dans celui de spoonProcess</li>
<li>Portabilité : La gestion des étapes se fait en shell</li>
</ul>