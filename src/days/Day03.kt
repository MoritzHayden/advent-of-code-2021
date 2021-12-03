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
        val lifeSupportRating: Int
        val counter = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        var oxygenRegex = ""
        var cO2Regex = ""
        var oxygenRating = ""
        var cO2Rating = ""
        var oxygenFound = false
        var cO2Found = false
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
            if (i >= 0) {
                oxygenRegex += "1"
                cO2Regex += "0"
            } else {
                oxygenRegex += "0"
                cO2Regex += "1"
            }
        }

        for (i in counter.indices.reversed()) {
            for (line in input) {
                if (!oxygenFound && line.matches(oxygenRegex.toRegex())) {
                    oxygenRating = line
                    oxygenFound = true
                }
                if (!cO2Found && line.matches(cO2Regex.toRegex())) {
                    cO2Rating = line
                    cO2Found = true
                }
                if (oxygenFound && cO2Found) {
                    break
                }
            }
            if (oxygenFound && cO2Found) {
                break
            } else {
                if (!oxygenFound) {
                    oxygenRegex = oxygenRegex.substring(0, i) + '.' + oxygenRegex.substring(i + 1)
                }
                if (!cO2Found) {
                    cO2Regex = cO2Regex.substring(0, i) + '.' + cO2Regex.substring(i + 1)
                }
            }
        }

        println(oxygenRating + " -> " + Integer.parseInt(oxygenRating, 2))
        println(cO2Rating + " -> " + Integer.parseInt(cO2Rating, 2))

        // Not the following: 866578
        lifeSupportRating = Integer.parseInt(oxygenRating, 2) * Integer.parseInt(cO2Rating, 2)
        return lifeSupportRating
    }
}
