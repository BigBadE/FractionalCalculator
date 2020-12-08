# FractionalCalculator

# How to build
The Gradle wrapper is included, run ./gradlew clean shadowJar to build the jar.

Lombok is used, so if you use IntelliJ without the Lombok plugin,

Go to Build, Execution, Deployment -> Build Tools -> Gradle ->

Build with/Run tests with, and select Gradle not IntelliJ IDEAD

# How to test
Run ./gradlew clean test --tests "software.bigbade.fractioncalculator.FracCalcTestFinal"

This will run all the tests in FracCalcTestFinal

# Modules
- main-input
    - UI, passes equation to parser. Depends on math for parser output to render. Uses JavaFX.
- parser
    - Parses String equation to a set of values/expressions using ANTLR4
- math
    - Handles mathematical expressions on values, provides value types, and renders values using an AnswerConsumer
- console-input
    - main-input with no GUI
