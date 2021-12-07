package days

import readFile
import kotlin.math.absoluteValue

class Day07 {
    fun solveAll() {
        println("Solving day 7...")
        println("Part 1: ${solvePart1()}")
        println("Part 2: ${solvePart2()}")
    }

    private fun solvePart1(): Int {
        val input = readFile("Day07").first().split(",").map { it.toInt() }

        return calculateFuelConsumption(input)
    }

    private fun solvePart2(): Long {
        val input = readFile("Day07").first().split(",").map { it.toInt() }

        return calculateFuelConsumptionScaled(input)
    }

    private fun calculateFuelConsumption(horizontalPositions: List<Int>): Int {
        var fuelCost = Int.MAX_VALUE
        var tempFuelCost: Int

        for (i in 0 .. horizontalPositions.maxOrNull()!!) {
            var distance = 0
            for (j in horizontalPositions) {
                distance += (j - i).absoluteValue
            }
            tempFuelCost = distance
            if (tempFuelCost < fuelCost) fuelCost = tempFuelCost
        }

        return fuelCost
    }

    private fun calculateFuelConsumptionScaled(horizontalPositions: List<Int>): Long {
        var fuelCost = Long.MAX_VALUE
        var tempFuelCost = 0L
        var distance = 0

        for (i in 0 .. horizontalPositions.maxOrNull()!!) {
            for (j in horizontalPositions.indices) {
                distance = (horizontalPositions[j] - i).absoluteValue
                for (k in 1..distance) {
                    tempFuelCost += k
                }
            }
            if (tempFuelCost < fuelCost) fuelCost = tempFuelCost
            tempFuelCost = 0L
        }

        return fuelCost
    }
}
