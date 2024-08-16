#!/bin/bash

# Check if an argument is provided
if [ $# -ne 1 ]; then
    echo "Usage: scripts/Run.sh <PATH_TO_JAVAFX>"
    exit 1
fi

# Assign the argument to a variable
PATH_TO_JAVAFX="$1"

# Check if the directory exists
if [ ! -d "$PATH_TO_JAVAFX" ]; then
    echo "JavaFX directory does not exist."
    exit 1
fi

# Run Taskpplication
java -cp "./build;./lib/h2-2.3.230.jar" --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml Taskpplication.Main