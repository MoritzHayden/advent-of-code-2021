package days

import readFile

class Day01 {
    fun solveAll() {
        println("Solving day 1...")
        println("Part 1: ${solvePart1()}")
        println("Part 2: ${solvePart2()}")
    }

    private fun solvePart1(): Int {
        var count = 0
        val input = readFile("Day01").map { it.toInt() }
        for (i in 1 until input.size) {
            if (input[i] > input[i-1]) {
                count++
            }
        }
        return count
    }

    private fun solvePart2(): Int {
        var count = 0
        val input = readFile("Day01").map { it.toInt() }
        for (i in 1 until (input.size - 2)) {
            val lastWindowSum = input[i-1] + input[i] + input[i+1]
            val currentWindowSum = input[i] + input[i+1] + input[i+2]
            if (currentWindowSum > lastWindowSum) {
                count++
            }
        }
        return count
    }
}
