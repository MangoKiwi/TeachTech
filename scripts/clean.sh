#!/bin/bash

FILE="./target/teachtech-0.0.1-SNAPSHOT.war"
if [ -f "$FILE" ]; then
	rm -rf ./target
fi

