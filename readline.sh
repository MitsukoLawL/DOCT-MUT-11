while read line
do
    echo "    Reading within the loop: [$line]"
    echo -n "    What do you want to say? "
    read -u 3 something
    echo "    You input: [$something]"
done 3<&0 <qq.sh
