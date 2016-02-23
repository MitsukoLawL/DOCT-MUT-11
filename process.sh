#!/bin/bash

cd spoonProcess
mvn compile
mvn exec:java -Dexec.mainClass="org.mutation11.maven.Main"  -Dexec.args="$1 $2"

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
	else
	    echo COMPILATION OF MUTATED PROJECT FAILED
	    echo ./EcrireCompilation "FAIL" 
	      # ./EcrireCompilation "FAIL"
	fi
  	# ./EcrireTestcase("./target/surefire-reports")

	# ecrit html
	cd ..

	#compilation de la classe, pendant la phase d'implementation, risque d'etre modifiée
	cd XML\&HTML/src
	javac toHTML.java
	mv toHTML.class ../
	cd .. 
	# ecrit Html
	java toHTML "../mutatedCode/target/surefire-reports/"

else
    echo COMPILATION OF ORIGINAL PROJECT FAILED
    echo ./EcrireCompilation "FAIL"
      # ./EcrireCompilation "FAIL"
fi
