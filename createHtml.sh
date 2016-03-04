#!/bin/bash 
	# ecrit html
	cd ..

	# compilation de la classe, pendant la phase d'implementation, risque d'etre modifiée
	cd XML\&HTML/src
	#		javac toHTML.java
	#		mv toHTML.class ../
	javac CreateHTML.java
	#		javac -classpath "../jdom-2.0.6.jar" MergeXML.java

	mv CreateHTML.class ../../
	cd ../../ 
	# ecrit Html
	#		java toHTML "../mutatedCode/target/surefire-reports/"
	java CreateHTML "mutatedCode/xmlResult/" "./Report/index.html" "./XML&HTML/test-bootstrap.xsl"
	#		java CreateHTML $1 $2 $3

	echo -e "\n \n"
	echo Op$2 appliqué
	echo "YOU WILL FIND THE REPORT ON REPORT/index.html"

