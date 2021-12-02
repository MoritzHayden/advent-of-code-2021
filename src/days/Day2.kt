package days

import readFile

class Day2 {
    fun solveAll() {
        println("Solving day 2...")
        println("Part 1: ${solvePart1()}")
        println("Part 2: ${solvePart2()}")
    }

    private fun solvePart1(): Int {
        var horizontalDelta = 0
        var verticalDelta = 0
        val input = readFile("Day2")
        for (i in input.indices) {
            val inputSplit = input[i].split(' ')
            val command = inputSplit[0]
            val amount = inputSplit[1].toInt()
            when (command) {
                "forward" -> horizontalDelta += amount
                "down" -> verticalDelta += amount
                "up" -> verticalDelta -= amount
            }
        }
        return horizontalDelta * verticalDelta
    }

    private fun solvePart2(): Int {
        var horizontalDelta = 0
        var verticalDelta = 0
        var aim = 0
        val input = readFile("Day2")
        for (i in input.indices) {
            val inputSplit = input[i].split(' ')
            val command = inputSplit[0]
            val amount = inputSplit[1].toInt()
            when (command) {
                "forward" -> {
                    horizontalDelta += amount
                    verticalDelta += (aim * amount)
                }
                "down" -> aim += amount
                "up" -> aim -= amount
            }
        }
        return horizontalDelta * verticalDelta
    }
}
