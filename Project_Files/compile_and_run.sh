#!/bin/bash

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
