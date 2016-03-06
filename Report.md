<!-- Une analyse critique de votre travail, quelle est l'architecture (dev et opérationnel) mise en oeuvre dans votre framework, quelles sont ses forces et ses faiblesses, ...  -->


Architecture
========

Dessin

## SpoonProcess ##
Processus de modification du code dans toBeMutated/

## mutatedCode ##
Les sources de toBeMutated/ sont envoyés dans le dossier mutatedCode/src/
Il représente le projet sur lequel nous allons travailler.
Les tests unitaires à appliquer à notre projet doit être placé dans mutatedCode/test/

Projet maven à muter.

## XML&HTML ##
Processus qui gère les xml des tests, et qui à la fin de toutes les transformations, génère le rapport HTML


## Report ##
Dossier où est généré le rapport HTML

Force :
--------
Processus détaché/indépendant du projet à tester.

Faiblesse :
--------
Sources dupliquées
SpoonProcess : Un dossier peut-être un peu trop gros pour ce qu'il fait
On ne peut appliquer au code, qu'un seul opérateur à la fois
