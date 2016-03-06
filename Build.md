<!-- Comment construire votre framework -->

Mettre ses sources dans spoonProcess/toBeMutated/

Mettre ses sources dans mutatedCode/src
Mettre ses tests dans mutatedCode/test

Utilisation interactive :
Lancer consoleInterface.sh
OU
Utilisation script :
Ecrire les mutations à executer dans ./execution.sh.
Lancer execution.sh

Les opérateurs disponibles sont Op1, Op9, Op12, Op13, Op16, la description est (dans Specifications.md)
"L'essentiel" :
Op1 : Transforme des attributs protected en privé -> Bloque à la compilation (mvn clean compile)
Op12 : Transforme des opérateurs arithmétique -> Bloque au test Unitaire (mvn test)

Les selecteurs sont des pourcentages, parmis les candidats nous appliquons en s% à notre projet entier.
