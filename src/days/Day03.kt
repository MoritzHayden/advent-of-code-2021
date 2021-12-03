package days

import readFile

class Day03 {
    fun solveAll() {
        println("Solving day 3...")
        println("Part 1: ${solvePart1()}")
        println("Part 2: ${solvePart2()}")
    }

    private fun solvePart1(): Int {
        val powerConsumption: Int
        val counter = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        var gammaRate = ""
        var epsilonRate = ""
        val input = readFile("Day03")

        for (i in input.indices) {
            for (j in 0 until input[i].length) {
                if (input[i][j] == '1') {
                    counter[j]++
                } else {
                    counter[j]--
                }
            }
        }

        for (i in counter) {
            if (i > 0) {
                gammaRate += "1"
                epsilonRate += "0"
            } else {
                gammaRate += "0"
                epsilonRate += "1"
            }
        }

        powerConsumption = Integer.parseInt(gammaRate, 2) * Integer.parseInt(epsilonRate, 2)
        return powerConsumption
    }

    private fun solvePart2(): Int {
        return 0
    }
}
