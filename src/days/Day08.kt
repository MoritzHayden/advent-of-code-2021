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

        var sortedVals: MutableList<String> = mutableListOf()
        var tempNumberList: MutableList<String> = mutableListOf()
        var signalMappingList: MutableList<MutableList<String>> = mutableListOf()

        val iterator = input.iterator()
        while(iterator.hasNext()){
            val item = iterator.next()
            signalMappingList.add(mapSignals(item.split(" | ").first().split(" ")))
        }

        for (i in input) {
            signalMappingList.add(mapSignals(i.split(" | ").first().split(" ")))
        }

        var counter = 0
        for (i in input.indices) {
            input[i].split(" | ").last().split(" ").forEach {
                sortedVals.add(sortString(it))
            }
            for (j in sortedVals) {
                tempNumberList.add(signalMappingList[i].indexOf(j).toString())
            }

            counter += concatenateToInt(tempNumberList)

            println("mapping: ${signalMappingList[i]}")
            println("sortedVals: $sortedVals")
            println("tempNumberList: $tempNumberList -> ${concatenateToInt(tempNumberList)}")
            println("counter: $counter")

            tempNumberList.clear()
            sortedVals.clear()
        }

        return counter
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

    private fun mapSignals(inputSignals: List<String>): MutableList<String> {
        val knownValues = mutableListOf("", "", "", "", "", "", "", "", "", "")

        val lengthFiveInputs: MutableList<String> = mutableListOf() // 2, 3, or 5
        val lengthSixInputs: MutableList<String> = mutableListOf() // 0, 6, or 9
        for (i in inputSignals.indices) {
            when (inputSignals[i].length) {
                2 -> knownValues[1] = sortString(inputSignals[i])
                3 -> knownValues[7] = sortString(inputSignals[i])
                4 -> knownValues[4] = sortString(inputSignals[i])
                5 -> lengthFiveInputs.add(sortString(inputSignals[i]))
                6 -> lengthSixInputs.add(sortString(inputSignals[i]))
                7 -> knownValues[8] = sortString(inputSignals[i])
            }
        }

        // Find 3
        for (i in lengthFiveInputs) {
            var iChars = i.toCharArray()
            if (iChars.contains(knownValues[1].toCharArray()[0]) &&
                iChars.contains(knownValues[1].toCharArray()[1])) {
                knownValues[3] = sortString(i)
            }
        }
        lengthFiveInputs.removeIf { it == knownValues[3] }

        // Find 6
        for (i in lengthSixInputs) {
            var iChars = i.toCharArray()
            if (!(iChars.contains(knownValues[1].toCharArray()[0]) &&
                  iChars.contains(knownValues[1].toCharArray()[1]))) {
                knownValues[6] = sortString(i)
            }
        }
        lengthSixInputs.removeIf { it == knownValues[6] }

        // Find 9
        for (i in lengthSixInputs) {
            var iChars = i.toCharArray()
            if (iChars.contains(knownValues[4].toCharArray()[0]) &&
                iChars.contains(knownValues[4].toCharArray()[1]) &&
                iChars.contains(knownValues[4].toCharArray()[2]) &&
                iChars.contains(knownValues[4].toCharArray()[3])) {
                knownValues[9] = sortString(i)
            }
        }
        lengthSixInputs.removeIf { it == knownValues[9] }

        println("lengthFiveInputs: $lengthFiveInputs")
        println("lengthSixInputs: $lengthSixInputs")

        // Find 0
        knownValues[0] = sortString(lengthSixInputs[0])

        // Find 2
        knownValues[2] = sortString(lengthFiveInputs[0]) //TODO: Might need to swap with 5

        // Find 5
        knownValues[5] = sortString(lengthFiveInputs[1]) //TODO: Might need to swap with 2

        return knownValues
    }

    private fun concatenateToInt(input: List<String>): Int {
        var output = ""
        input.forEach {
            output += it
        }
        return output.toInt()
    }

    private fun sortString(input: String): String {
        return input.toCharArray().sorted().joinToString("")
    }
}
