#!/bin/bash
 echo "Usage: ./autocode.sh \$Table \$Entity \$Description \$package"
sh -c "nohup mvn compile > /dev/null"
echo "Table $1 "
echo "Entity $2"
echo "Description $3"
echo "package $4"
echo "Starting autocode..."
sh -c "nohup mvn exec:java -Dexec.mainClass=com.baidu.autocoder.AutoCoder -Dexec.args='$1 $2 $3 $4' > /dev/null"
echo "Finish autocode."
