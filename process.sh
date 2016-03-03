#!/bin/bash

cd spoonProcess
mvn compile
mvn exec:java -Dexec.mainClass="org.mutation11.maven.Main"  -Dexec.args="$1 $2 $3"

if [ $? -eq 0 ]; then
	echo COMPILATION OF ORIGINAL PROJECT SUCEEDED
	cd ..
	# ./EcrireMutations mutation
	# ./EcrireSelecteur selecteur

	cd mutatedCode

	DIR="./target/classes"

	mvn clean compile

	if [ $? -eq 0 ]; then
		mvn test
		# ./EcrireCompilation "SUCCESS"	

		# ./EcrireTestcase("./target/surefire-reports")

		# ecrit html
		cd ..

		# compilation de la classe, pendant la phase d'implementation, risque d'etre modifiée
		cd XML\&HTML/src
#		javac toHTML.java
#		mv toHTML.class ../
		javac MergeXML.java
#		javac -classpath "../jdom-2.0.6.jar" MergeXML.java

		mv MergeXML.class ../../
		cd ../../ 
		# ecrit Html
#		java toHTML "../mutatedCode/target/surefire-reports/"
		java MergeXML "mutatedCode/target/surefire-reports/" "./Report/index.html" "./XML&HTML/test-bootstrap.xsl"
		
		echo -e "\n \n"
		echo Op$2 appliqué
		echo "YOU WILL FIND THE REPORT ON REPORT/index.html"
		
	else
	    echo COMPILATION OF MUTATED PROJECT FAILED
	    echo ./EcrireCompilation "FAIL" 
	      # ./EcrireCompilation "FAIL"
	fi
  	
else
    echo COMPILATION OF ORIGINAL PROJECT FAILED
    echo ./EcrireCompilation "FAIL"
      # ./EcrireCompilation "FAIL"
fi
