#!/bin/bash


cd spoonProcess
mvn compile
mvn exec:java -Dexec.mainClass="org.mutation11.maven.Main"

cd ..
# for VARIABLE in 1 2 3 4 5 .. N
# do

  # ./EcrireMutations mutation
  # ./EcrireSelecteur selecteur

cd mutatedCode

DIR="./target/classes"
  mvn clean compile

  if [ "$(ls -A $DIR)" ]; then
      echo "Take action $DIR is not Empty"
      # ./EcrireCompilation "SUCCESS"
      mvn test
      # ./EcrireTestcase("./target/surefire-reports")
  else
      echo "$DIR is Empty"
      # ./EcrireCompilation "FAIL"
  fi
# done
# ./EcrireHTML()
