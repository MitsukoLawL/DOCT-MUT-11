#!/bin/bash


# compilation de la classe, pendant la phase d'implementation, risque d'etre modifiée
cd XML\&HTML/src
#Create a XML to save op and selec
javac *.java

mv MergeXML.class ../../
mv CreatHTML.class ../../
mv CreateXML.class ../../
cd ../../ 


cd spoonProcess

mvn compile
mvn exec:java -Dexec.mainClass="org.mutation11.maven.Main"  -Dexec.args="$1 $2 $3 $4"

if [ $? -eq 0 ]; then
	echo COMPILATION OF ORIGINAL PROJECT SUCEEDED
	cd ..

	cd mutatedCode
	
	DIR="./target/classes"

	mvn clean compile


	if [ $? -eq 0 ]; then
		# java CreateXML $2 $3 "mutatedCode/xmlResult/"
		
		#cd mutatedCode
		mvn test
		# ./EcrireCompilation "SUCCESS"	

		# ecrit html
		cd ..

		# # compilation de la classe, pendant la phase d'implementation, risque d'etre modifiée
		# cd XML\&HTML/src

		# javac *.java

		# mv MergeXML.class ../../
		# mv CreatHTML.class ../../
		# mv CreateXML.class ../../
		# cd ../../ 
		# ecrit Xml - Html -> last version
		# java MergeXML "mutatedCode/target/surefire-reports/" "./Report/index.html" "./XML&HTML/test-bootstrap.xsl"
		# ecrit XML
		java MergeXML "mutatedCode/target/surefire-reports/" $2 $3 "./xmlResult/"

		
		echo -e "\n \n"
		echo $2 appliqué
		echo "YOU WILL FIND THE REPORT ON report/index.html"
		
	else
		cd ..
		java CreateXML $2 $3 "./xmlResult/"
	    echo COMPILATION OF MUTATED PROJECT FAILED
	    echo ./EcrireCompilation "FAIL" 
	      # ./EcrireCompilation "FAIL"
	fi
  	
else
    echo COMPILATION OF ORIGINAL PROJECT FAILED
    echo ./EcrireCompilation "FAIL"
      # ./EcrireCompilation "FAIL"
fi
