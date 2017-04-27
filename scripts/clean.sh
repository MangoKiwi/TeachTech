#!/bin/bash

FILE="./target/teachtech-0.0.1-SNAPSHOT.war"
[ -f "$FILE" ] && rm -rf ./target
echo "Remove garbage successfully"