#!/bin/sh
DIR=OS-Process-Watcher
MVNW_FILE="mvnw"
JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64/bin/java

if [ -d "$DIR" ]; then
    echo "Git already cloned"
else
    git clone https://github.com/LukasMazl/OS-Process-Watcher
fi

cd "./$DIR/"

if [ -d "target" ]; then
	echo "Target already exists"
else
	/bin/sh "$MVNW_FILE" "clean" "install"
fi

clear

"$JAVA_HOME" -jar ./target/OperationSystems-1.0-SNAPSHOT.jar -d -master -tasksFile,Socket,Memory
