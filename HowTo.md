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

Les opérateurs disponibles sont Op1, Op9, Op12, Op13, Op16, la description est (dans Specifications.md)
Les deux opérateurs qui suffiront à tester les erreurs de test ou de compilation :
Op1 : Transforme des attributs protected en privé -> Bloque à la compilation (mvn clean compile)
Op12 : Transforme des opérateurs arithmétique -> Bloque au test Unitaire (mvn test)

Les selecteurs sont des pourcentages, parmis les candidats nous appliquons en s% à notre projet entier.
