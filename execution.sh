#!/bin/bash

./process.sh toBeMutated/ Op1 100 

# ./process.sh toBeMutated/ Op1 100 
# ./process.sh toBeMutated/ Op13 100 
# ./process.sh toBeMutated/ Op9 100 


java CreatHTML "mutatedCode/xmlResult/" "./Report/index.html" "./XML&HTML/test-bootstrap.xsl"

