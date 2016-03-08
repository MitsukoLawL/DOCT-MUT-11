<!-- Une analyse critique de votre travail, quelle est l'architecture (dev et opérationnel) mise en oeuvre dans votre framework, quelles sont ses forces et ses faiblesses, ...  -->


Architecture
========

Représentation de la chaîne de production
![Execution](https://github.com/MitsukoLawL/DOCT-MUT-11/blob/master/doc/img/mutationTesting.png "Dessin représentant une execution")

Le framework est divisé en plusieurs parties
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
<li>./execution.sh
<br/>Qui appelera une sequence de ./process.sh puis CreatHTML.java à la fin
<br/>C'est un script pour utiliser le framework.
</li>
<li>./consoleInterface.sh
<br/>Qui appelera une sequence de ./process.sh puis CreatHTML.java à la fin
<br/>C'est une interface interactive pour utiliser le framework.
</li>
</ul>

Force :
--------
Processus détaché/indépendant du projet à tester.

Faiblesse :
--------
Sources dupliquées
SpoonProcess : Un dossier peut-être un peu trop gros pour ce qu'il fait
On ne peut appliquer au code, qu'un seul opérateur à la fois
