#!/bin/zsh

javac -d classes -cp "lib/*" $(find src -name "*.java")

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
else
    echo "Compilation failed."
fi
