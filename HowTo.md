<!-- Une présentation de votre travail sous la forme d'un tutoriel a suivre. -->

# How To #

1. Mettre ses sources dans spoonProcess/toBeMutated/
2. Mettre ses sources dans mutatedCode/src
3. Mettre ses tests dans mutatedCode/test

-
## Utilisation interactive : #
Lancer consoleInterface.sh

## Utilisation script : #
Ecrire les mutations à executer dans ./execution.sh.
Lancer execution.sh

Les opérateurs disponibles sont <b>Op1, Op9, Op12, Op13, Op16,</b> (la description est dans Specifications.md). <br/>
Les deux opérateurs qui suffiront à tester les erreurs de test ou de compilation :
<b>Op1</b> : Transforme des attributs protected en privé -> Bloque à la compilation (mvn clean compile)
<b>Op12</b> : Transforme des opérateurs arithmétique -> Bloque au test Unitaire (mvn test)

Les selecteurs sont des pourcentages, nous appliquons s% des candidats à notre projet entier.
