#!/bin/bash

# Supprime le dossier contenant les fichiers temporaires
# avant de demarrer le processus de mutation
if [ "$(ls -A xmlResult/)" ]; then
  cd xmlResult/
  rm *
  cd ..
fi


##############################
##### NE PAS ECRIRE AU DESSUS
# Utilisation : ./process.sh "dossier à muter" "nom mutant" "pourcentage"

# ./process.sh toBeMutated/ Op1 100 ../xmlResult/
./process.sh toBeMutated/ Op12 100 ../xmlResult/
#./process.sh toBeMutated/ Op12 50 ../xmlResult/

# ./process.sh toBeMutated/ Op16 100 ../xmlResult/
#./process.sh toBeMutated/ Op9 100 ../xmlResult/
./process.sh toBeMutated/ Op13 100 ../xmlResult/

##### NE PAS ECRIRE EN DESSOUS
##############################

java CreatHTML "./xmlResult/" "./Report/index.html" "./XML&HTML/test-bootstrap.xsl"
