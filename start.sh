#!/bin/sh
DIR=OS-Process-Watcher
MVNW_FILE="mvnw"

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

java -jar ./target/OperationSystems-1.0-SNAPSHOT.jar -master --user
