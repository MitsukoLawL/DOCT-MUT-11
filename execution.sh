#!/bin/bash


cd spoonProcess
mvn compile
mvn exec:java -Dexec.mainClass="org.mutation11.maven.Main"

if [ $? -eq 0 ]; then
	echo COMPILATION OF MUTATION SUCEEDED
	cd ..
	# for VARIABLE in 1 2 3 4 5 .. N
	# do

		# ./EcrireMutations mutation
		# ./EcrireSelecteur selecteur

		cd mutatedCode

		DIR="./target/classes"

		mvn clean compile

		if [ $? -eq 0 ]; then
			mvn test
			# ./EcrireCompilation "SUCCESS"	
		else
		    echo MVN COMPILE FAILED
		    echo ./EcrireCompilation "FAIL" 
		      # ./EcrireCompilation "FAIL"
		fi
	  	# ./EcrireTestcase("./target/surefire-reports")
	# done
	# ./EcrireHTML()
else
    echo COMPILATION OF MUTATION FAILED
    echo ./EcrireCompilation "FAIL"
      # ./EcrireCompilation "FAIL"
fi
