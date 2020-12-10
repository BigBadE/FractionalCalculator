# FractionalCalculator

# How to test
Run ``git clone "https://github.com/BigBadE/FractionalCalculator``

Run ``./gradlew clean test --tests "software.bigbade.fractioncalculator.FracCalcTestFinal"``

This will run all the tests in FracCalcTestFinal

# How to build
The Gradle wrapper is included, run ``./gradlew clean shadowJar`` to build the jar.

#Lombok + Fixing IntelliJ Errors
Lombok is used, so if you use IntelliJ without the Lombok plugin,

Go to Build, Execution, Deployment -> Build Tools -> Gradle ->

Build with/Run tests with, and select Gradle not IntelliJ IDEA

# Modules
- parser
    - Parses String equation to a set of values/expressions using ANTLR4
- math
    - Handles mathematical expressions on values, provides value types, and renders values using an AnswerConsumer
- console-input
    - main-input with no GUI
