#!/bin/bash

##############################
##### NE PAS ECRIRE AU DESSUS
# Utilisation : ./process.sh "dossier à muter" "nom mutant" "pourcentage"

./process.sh toBeMutated/ Op1 100
./process.sh toBeMutated/ Op12 100

# ./process.sh toBeMutated/ Op12 50
# ./process.sh toBeMutated/ Op9 100
# ./process.sh toBeMutated/ Op13 100

##### NE PAS ECRIRE EN DESSOUS
##############################

java CreatHTML "./xmlResult/" "./Report/index.html" "./XML&HTML/test-bootstrap.xsl"
