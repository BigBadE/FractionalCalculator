# FractionalCalculator

# How to build
The Gradle wrapper is included, run ./gradlew shadowJar to build the jar.

If you have gradle that isn't necessary, but make sure you are running Gradle 6

# Modules
- main-input
    - UI, passes equation to parser. Depends on math for parser output to render. Uses JavaFX.
- parser
    - Parses String equation to a set of values/expressions using ANTLR4
- math
    - Handles mathematical expressions on values, provides value types, and renders values using an AnswerConsumer
