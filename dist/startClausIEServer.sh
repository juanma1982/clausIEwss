#!/bin/bash

DIR=`dirname $0`
echo $*
java -XX:MaxPermSize=512m -Xms512m -Xmx3072m -jar clausieServer.jar -cp $DIR/../clausie_lib/stanford-parser.jar:$DIR/../clausie_lib/stanford-parser-2.0.4-models.jar:$DIR/../clausie_lib/jopt-simple-4.4.jar

