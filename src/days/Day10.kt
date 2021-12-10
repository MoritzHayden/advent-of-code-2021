package days

import java.util.ArrayDeque
import readFile

class Day10 {
    fun solveAll() {
        println("Solving day 10...")
        println("Part 1: ${solvePart1()}")
        println("Part 2: ${solvePart2()}")
    }

    private fun solvePart1(): Int {
        val input = readFile("Day10")
        val openingChars = listOf<Char>('(', '[', '{', '<')
        val closingChars = listOf<Char>(')', ']', '}', '>')
        val charScores = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
        var totalScore = 0
        var stack = ArrayDeque<Char>()

        for (line in input) {
            var offendingChar: Char? = null
            val lineChars = line.toCharArray()
            for (char in lineChars) {
                if (openingChars.contains(char)) {
                    // Opening character
                    stack.push(char)
                } else if (closingChars.contains(char)) {
                    // Closing character
                    if (stack.isEmpty()) {
                        // Invalid if the stack is empty
                        offendingChar = char
                        break
                    } else {
                        val openingChar = stack.pop()
                        val openingCharIndex = openingChars.indexOf(openingChar)
                        val closingCharIndex = closingChars.indexOf(char)
                        if (openingCharIndex != closingCharIndex) {
                            // Invalid if the opening and closing characters don't match
                            offendingChar = char
                            break
                        }
                    }
                }
            }

            if (offendingChar != null) {
                totalScore += charScores[offendingChar]!!
            }
        }

        return totalScore
    }

    private fun solvePart2(): Long {
        val input = readFile("Day10")
        val openingChars = listOf<Char>('(', '[', '{', '<')
        val closingChars = listOf<Char>(')', ']', '}', '>')
        val charScores = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)
        var stack = ArrayDeque<Char>()
        val totalScores = mutableListOf<Long>()

        for (line in input) {
            var lineValid = true
            val lineChars = line.toCharArray()
            for (char in lineChars) {
                if (openingChars.contains(char)) {
                    // Opening character
                    stack.push(char)
                } else if (closingChars.contains(char)) {
                    // Closing character
                    if (stack.isEmpty()) {
                        // Invalid if the stack is empty
                        lineValid = false
                        break
                    } else {
                        val openingChar = stack.pop()
                        val openingCharIndex = openingChars.indexOf(openingChar)
                        val closingCharIndex = closingChars.indexOf(char)
                        if (openingCharIndex != closingCharIndex) {
                            // Invalid if the opening and closing characters don't match
                            lineValid = false
                            break
                        }
                    }
                }
            }

            if (lineValid) {
                var lineScore = 0L
                for (item in stack) {
                    val openingCharIndex = openingChars.indexOf(item)
                    val closingChar = closingChars[openingCharIndex]

                    lineScore *= 5
                    lineScore += charScores[closingChar]!!
                }
                totalScores.add(lineScore)
            }

            stack.clear()
        }

        totalScores.sort()

        return totalScores[totalScores.size / 2]
    }
}
