package days

import readFile
import toInt

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

        powerConsumption = gammaRate.toInt() * epsilonRate.toInt()
        return powerConsumption
    }

    private fun solvePart2(): Int {
        val oxygenInput = readFile("Day03").toMutableList()
        val cO2Input = readFile("Day03").toMutableList()
        var oxygenRating = getRating(oxygenInput, "oxygen")
        var cO2Rating = getRating(cO2Input, "co2")
        return oxygenRating * cO2Rating
    }

    private fun getRating(input: MutableList<String>, type: String): Int {
        var rating = ""
        var searchChar: Char

        for (i in 0 until 12) {
            var currentCount = 0
            for (j in input.indices) {
                if (input[j][i] == '1') {
                    currentCount++
                } else {
                    currentCount--
                }
            }

            searchChar = if (currentCount >= 0) '1' else '0'

            when(type) {
                "oxygen" -> input.removeIf{ it[i] != searchChar }
                "co2" -> input.removeIf{ it[i] == searchChar }
            }

            if (input.size == 1) {
                rating = input[0]
                break
            }
        }

        return rating.toInt()
    }
}
