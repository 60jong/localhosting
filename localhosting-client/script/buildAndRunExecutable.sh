#!/bin/bash

# build jar
echo -e '### BUILD localhosting.jar start ###\n'
./gradlew jar
echo -e '\n### BUILD localhosting.jar success ###\n'

# build localhosting.exe
echo -e '\n### BUILD localhosting.exe start ###\n'
launch4jc ./script/config.xml
echo -e '\n### BUILD localhosting.exe success ###\n'

echo -e '\n### RUN localhosting.exe ###'
./executable/localhosting.exe -p 8888 -name ugaeng

read -p "Press enter to continue"