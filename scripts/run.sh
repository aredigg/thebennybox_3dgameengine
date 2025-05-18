#!/bin/zsh

java --enable-native-access=ALL-UNNAMED -XstartOnFirstThread -cp "classes:lib/*" -Djava.library.path="native/macos" "jackalworks.engine.Main"
