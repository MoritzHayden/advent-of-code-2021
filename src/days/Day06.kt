package days

import readFile

class Day06 {
    fun solveAll() {
        println("Solving day 6...")
        println("Part 1: ${solvePart1()}")
        println("Part 2: ${solvePart2()}")
    }

    private fun solvePart1(): Long {
        val input = readFile("Day06")
        val fishSchool = parseInput(input)

        return calculatePopulation(fishSchool, 80)
    }

    private fun solvePart2(): Long {
        val input = readFile("Day06")
        val fishSchool = parseInput(input)

        return calculatePopulation(fishSchool, 256)
    }

    private fun parseInput(input: List<String>): MutableList<Int> {
        return input[0].split(",").map { it.toInt() }.toMutableList()
    }

    private fun calculatePopulation(fishSchool: MutableList<Int>, days: Int): Long {
        var newFishCount: Long = 0
        var population = mutableListOf<Long>(0, 0, 0, 0, 0, 0, 0, 0, 0)
        for (i in 0 .. 8) {
            population[i] = fishSchool.count{ it == i }.toLong()
        }

        for (i in 0 until days) {
            for (j in population.indices) {
                if (j == 0) {
                    newFishCount = population[0]
                    population[0] = 0
                }
                if (j > 0) {
                    population[j-1] += population[j]
                    population[j] = 0
                }
            }
            population[6] += newFishCount
            population[8] += newFishCount
        }
        return population.sum()
    }
}
