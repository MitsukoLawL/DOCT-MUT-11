<!-- Une analyse critique de votre travail, quelle est l'architecture (dev et opérationnel) mise en oeuvre dans votre framework, quelles sont ses forces et ses faiblesses, ...  -->


Architecture
========

Représentation de la chaîne de production
![Execution](https://github.com/MitsukoLawL/DOCT-MUT-11/blob/master/doc/img/mutationTesting.svg "Dessin représentant une execution")

Le framework est divisé en plusieurs parties
### SpoonProcess ##
Processus de modification du code dans toBeMutated/
Composé d'un Main.java et de classes d'opérateurs de mutation.

### mutatedCode ##
Les sources de toBeMutated/ sont envoyés dans le dossier mutatedCode/src/
Il représente le projet sur lequel nous allons travailler.
Les tests unitaires à appliquer à notre projet doit être placé dans mutatedCode/test/

Nous lançons les tests à l'aide d'un mvn test
### XML&HTML ##
Processus qui gère les xml des tests, et qui à la fin de toutes les transformations, génère le rapport HTML


### Report ##
Dossier où est généré le rapport HTML

Force :
--------
Processus détaché/indépendant du projet à tester.

Faiblesse :
--------
Sources dupliquées
SpoonProcess : Un dossier peut-être un peu trop gros pour ce qu'il fait
On ne peut appliquer au code, qu'un seul opérateur à la fois
