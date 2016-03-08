<!-- Une analyse critique de votre travail, quelle est l'architecture (dev et opérationnel) mise en oeuvre dans votre framework, quelles sont ses forces et ses faiblesses, ...  -->


Architecture
========

Représentation de la chaîne de production
![Execution](https://github.com/MitsukoLawL/DOCT-MUT-11/blob/master/doc/img/mutationTesting.png "Dessin représentant une execution")

<b>Le framework est divisé en plusieurs parties que nous allons décrire ci-dessous</b>
### SpoonProcess/ ##
Processus de modification du code dans toBeMutated/
Composé d'un Main.java et de classes d'opérateurs de mutation.

### mutatedCode/ ##
Les sources de toBeMutated/ sont envoyés dans le dossier mutatedCode/src/
Il représente le projet sur lequel nous allons travailler.
Les tests unitaires à appliquer à notre projet doit être placé dans mutatedCode/test/

### XML&HTML/ ##
Processus qui gère les xml des tests, et qui à la fin de toutes les transformations, génère le rapport HTML


### Report/ ##
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
De plus, ces chiffres sont accompagnés de deux graphes (Highcharts) afin d'illustrer les résultats.


### Bash ##
Pour relier chaque parties du framework, nous utilisons des scripts shell.
<ul>
<li>./process.sh [dossier Source] [Operation] [Selecteur]
<br/>Qui permettra de faire tout le process depuis toBeMutated/ à xmlResult/ (cf Dessin)
</li>
<li> $ ./consoleInterface <br/>
    Qui demandera le "Dossier Source", "le mutant", "le selecteur" de facon interactive</li></ul>
    ![Exemple consoleInterface](https://github.com/MitsukoLawL/DOCT-MUT-11/blob/master/doc/img/consoleInterface.png "Exemple d'utilisation de consoleInterface")
    <p>Lorsqu'on entrera "exit", cela mettra fin au traitement de mutant et un HTML sera généré (grâce à l'appel de la fonction CreatHTML.java)</p>
<ul><li> $ ./execution.sh
    Où nous ajoutons les lignes <i>./process.sh "Dossier Source" "mutant" "selecteur"</i>
    Pour autant de mutation que nous souhaitons appliquer.</li></ul>
    ![Exemple execution.sh](https://github.com/MitsukoLawL/DOCT-MUT-11/blob/master/doc/img/execution.png "Exemple d'utilisation de ./execution.sh")
    <p>Après le dernier traitement de mutant, un HTML est généré (grâce à l'appel de la fonction CreatHTML.java)</p>

</ul>
    ![Exemple consoleInterface](https://github.com/MitsukoLawL/DOCT-MUT-11/blob/master/doc/img/consoleInterface.png "Exemple d'utilisation de consoleInterface")
    ![Exemple execution.sh](https://github.com/MitsukoLawL/DOCT-MUT-11/blob/master/doc/img/execution.png "Exemple d'utilisation de ./execution.sh")

Force :
--------
Processus de transformation est indépendant du projet à tester.

Faiblesse :
--------
Sources dupliquées
SpoonProcess : Un dossier peut-être un peu trop gros pour ce qu'il fait
On ne peut appliquer au code, qu'un seul opérateur à la fois
