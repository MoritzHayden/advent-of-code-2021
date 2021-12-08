package days

import readFile

class Day08 {
    fun solveAll() {
        println("Solving day 8...")
        println("Part 1: ${solvePart1()}")
        println("Part 2: ${solvePart2()}")
    }

    private fun solvePart1(): Int {
        val input = readFile("Day08")

        return countUniqueOutputs(input)
    }

    private fun solvePart2(): Int {
        val input = readFile("Day08")
        val inputSignals = input.first().split(" | ").first().split(" ")
        val signalMapping = mapSignals(inputSignals)
        println(signalMapping)

        return 0
    }

    private fun countUniqueOutputs(input: List<String>): Int {
        val outputValues: MutableList<String> = mutableListOf()
        for (i in input.indices) {
            var outputVals = input[i].split(" | ")[1]
            outputVals.split(" ").forEach { outputVal ->
                outputValues.add(outputVal)
            }
        }

        return outputValues.count { it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7 }
    }

    private fun mapSignals(inputSignals: List<String>): MutableMap<String, String> {
        val outputValues: MutableMap<String, String> = mutableMapOf()
        // Format: first value is very top then clockwise. Middle bar is last.
        var trackedValues = mutableListOf<Char>(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ')

        // Map the known values
        inputSignals.forEach { inputSignal ->
            if (inputSignal.length == 2) {
                outputValues.put(sortString(inputSignal), "1")
            } else if (inputSignal.length == 3) {
                outputValues.put(sortString(inputSignal), "7")
            } else if (inputSignal.length == 4) {
                outputValues.put(sortString(inputSignal), "4")
            } else if (inputSignal.length == 7) {
                outputValues.put(sortString(inputSignal), "8")
            }
        }

        // Find indices of known values
        var oneIndex = 0
        var fourIndex = 0
        var sevenIndex = 0
        var eightIndex = 0
        for (i in inputSignals.indices) {
            when (inputSignals[i].length) {
                2 -> oneIndex = i
                3 -> sevenIndex = i
                4 -> fourIndex = i
                7 -> eightIndex = i
            }
        }

        // Map signal of top value
        inputSignals[sevenIndex].toCharArray().forEach {
            if (!inputSignals[oneIndex].toCharArray().contains(it)) {
                trackedValues[0] = it
            }
        }

        println(inputSignals[oneIndex].toCharArray())
        println(inputSignals[sevenIndex].toCharArray())
        println("Top value: ${trackedValues[0]}")

        // trackedValues[0] = inputSignals[oneIndex].toCharArray()

        return outputValues
    }

    private fun concatenateToInt(input: List<String>): Int {
        var output = ""
        input.forEach {
            output += it
        }
        return output.toInt()
    }

    private fun sortString(input: String): String {
        return input.toCharArray().sorted().joinToString { "" }
    }
}
