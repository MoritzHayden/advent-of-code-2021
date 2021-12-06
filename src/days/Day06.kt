package days

import readFile

class Day06 {
    fun solveAll() {
        println("Solving day 6...")
        println("Part 1: ${solvePart1()}")
        println("Part 2: ${solvePart2()}")
    }

    private fun solvePart1(): Int {
        val input = readFile("Day06")
        val fishSchool = parseInput(input)

        return calculatePopulation(fishSchool, 80)
    }

    private fun solvePart2(): Int {
        val input = listOf("3,4,3,1,2") // Sample input
        // val input = readFile("Day06")
        val fishSchool = parseInput(input)

        return calculatePopulation(fishSchool, 256)
    }

    private fun parseInput(input: List<String>): MutableList<Int> {
        return input[0].split(",").map { it.toInt() }.toMutableList()
    }

    private fun calculatePopulation(fishSchool: MutableList<Int>, days: Int): Int {
        for (i in 0 until days) {
            for (j in 0 until fishSchool.size) {
                if (fishSchool[j] == 0) {
                    fishSchool[j] = 6
                    fishSchool.add(8)
                } else {
                    fishSchool[j]--
                }
            }
        }
        return fishSchool.size
    }
}
