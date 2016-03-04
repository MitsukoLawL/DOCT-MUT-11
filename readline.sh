#!/bin/bash 
         
# until [ -d "./spoonProcess/$folderSource" ]; do
until [ ! -z $folderSource ]; do
   echo "toBeMutated/ by default"
   $folderSource = "toBeMutated/"
   read -p "Please, enter your folder Source: " folderSource
done
while true; do
    echo -e "\n \n Operator : Op1, Op9, Op12, Op13, Op16"
    echo "Example : Op12"
    read -p "Please, enter your operator name (exit to quit): " op
    
    echo "Example : 100"
    read -p "Please, enter a percent (exit to quit) : " s
	
    if [ "$op" == "exit" ] || [ "$s" == "exit" ]
       then echo -e "\n end of mutation --> HTML step"
       break
    fi
    
    # If vars are not empty
    if [[ ! -z $folderSource && ! -z "$op" && ! -z "$s" ]]
       then ./process.sh $folderSource $op $s
       echo "\n $op $s applied"
    else echo "Wrong parameter, try again !"
    fi
done

echo -e "\n \n Author : Alicia Marin \n Fernandes Felipe \n Dahmoul Salah"
