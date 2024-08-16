@echo off

rem Check if an argument is provided
if "%1"=="" (
    echo Usage: scripts/Run.bat ^<PATH_TO_JAVAFX^>
    exit /b 1
)

rem Assign the argument to a variable
set PATH_TO_JAVAFX=%1

rem Check if the directory exists
if not exist "%PATH_TO_JAVAFX%" (
    echo JavaFX directory does not exist.
    exit /b 1
)

rem Create javadoc for classes in Taskpplication package and its subpackages
javadoc -d docs --module-path %PATH_TO_JAVAFX% --add-modules javafx.controls,javafx.fxml -sourcepath src\main\java -subpackages Taskpplication