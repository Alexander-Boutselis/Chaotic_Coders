#!/bin/bash

# Check if the "-c" flag was provided as a parameter
if [ "$1" == "-c" ]; then
    echo "Clearing terminal..."
    clear
fi

# Compile all .java files in the current directory
javac *.java

# Check if the compilation was successful
if [ $? -eq 0 ]; then
    echo "Compilation successful. Running Main..."
    echo ""
    # Run the Main class
    java Main
else
    echo "Compilation failed."
fi
