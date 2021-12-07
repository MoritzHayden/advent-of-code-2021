# Advent of Code 2021
My solutions for [Advent of Code 2021](https://adventofcode.com/2021), written in Kotlin

## Table of Contents
1. [How To Run](#How-To-Run)
2. [Util Functions](#Util-Functions)

### How To Run
1. Clone this repository to your local machine
2. Create a new configuration in IntelliJ IDEA and set `MainKt` as the main class
    - You may need to download any missing dependencies like OpenJDK or Kotlin, but IntelliJ IDEA will help you with this
3. Run the project!

### Util Functions
The `src/Utils.kt` file contains some helpful methods from [JetBrains](https://www.jetbrains.com/) that you may find useful.

- The `readFile(name: String)` function takes in a file name and returns a List<String>. For example:
    ```
    var count = 0
    val input = readFile("Day01").map { it.toInt() }
    for (i in 1 until input.size) {
        if (input[i] > input[i-1]) {
            count++
        }
    }
    ```
- The `String.toInt()` function is an extension method on the String class that transforms a string representation of a binary number into its integer representation. For example:
    ```
    if ("01001101".toInt() == 77) {
        // This is true
    }
    ```
- The `String.md5()` function is an extension method on the String class that transforms a string into a md5 hash. For example:
    ```
    if ("This is a test string".md5() == "c639efc1e98762233743a75e7798dd9c") {
        // This is true
    }
    ```
