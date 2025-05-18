#!/bin/zsh

java -XstartOnFirstThread --enable-native-access=ALL-UNNAMED -cp "classes:lib/*" -Djava.library.path="native/macos" "jackalworks.engine.HelloWorld"
