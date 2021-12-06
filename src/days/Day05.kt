package days

import readFile
import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.sign

class Day05 {
    fun solveAll() {
        println("Solving day 5...")
        println("Part 1: ${solvePart1()}")
        println("Part 2: ${solvePart2()}")
    }

    private fun solvePart1(): Int {
        val input = readFile("Day05")
        return countDangerousVents(input, false)
    }

    private fun solvePart2(): Int {
        val input = readFile("Day05")
        return countDangerousVents(input, true)
    }

    private fun countDangerousVents(input: List<String>, diagonals: Boolean): Int {
        val ventGrid = mutableMapOf<Pair<Int, Int>, Int>()

        input.forEach { it ->
            val coordinates = it.split(" -> ")
            val firstCoordinate = coordinates[0].split(",").map { it.toInt() }
            val secondCoordinate = coordinates[1].split(",").map { it.toInt() }

            val xIncline = (firstCoordinate[0] - secondCoordinate[0]).sign
            val yIncline = (firstCoordinate[1] - secondCoordinate[1]).sign

            val length = max(
                (firstCoordinate[0] - secondCoordinate[0]).absoluteValue,
                (firstCoordinate[1] - secondCoordinate[1]).absoluteValue
            )

            for (i in 0..length) {
                if (!diagonals && xIncline != 0 && yIncline != 0) break
                val currentCoordinate = Pair(firstCoordinate[0] + i * -xIncline, firstCoordinate[1] + i * -yIncline)
                ventGrid[currentCoordinate] = (ventGrid[currentCoordinate] ?: 0).plus(1)
            }
        }

        return ventGrid.count { it.value >= 2 }
    }
}
