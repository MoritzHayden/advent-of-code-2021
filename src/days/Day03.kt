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
        val lifeSupportRating: Int
        var oxygenRating = ""
        var cO2Rating = ""
        var searchChar: Char
        val oxygenInput = readFile("Day03").toMutableList()
        val cO2Input = readFile("Day03").toMutableList()

        // Oxygen Rating
        for (i in 0 until 12) {
            var currentCount = 0
            for (j in oxygenInput.indices) {
                if (oxygenInput[j][i] == '1') {
                    currentCount++
                } else {
                    currentCount--
                }
            }

            searchChar = if (currentCount >= 0) '1' else '0'

            oxygenInput.removeIf{ it[i] != searchChar }

            if (oxygenInput.size == 1) {
                oxygenRating = oxygenInput[0]
                break
            }
        }

        // CO2 Rating
        for (i in 0 until 12) {
            var currentCount = 0
            for (j in cO2Input.indices) {
                if (cO2Input[j][i] == '1') {
                    currentCount++
                } else {
                    currentCount--
                }
            }

            searchChar = if (currentCount >= 0) '1' else '0'

            cO2Input.removeIf{ it[i] == searchChar }

            if (cO2Input.size == 1) {
                cO2Rating = cO2Input[0]
                break
            }
        }

        lifeSupportRating = oxygenRating.toInt() * cO2Rating.toInt()
        return lifeSupportRating
    }
}
