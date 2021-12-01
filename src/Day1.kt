import java.io.File

class Day1 {
    fun solveAll() {
        println("Solving day 1...")
        println("Part 1: ${solvePart1()}")
        println("Part 2: ${solvePart2()}")
    }

    private fun solvePart1(): Int {
        var count = 1
        val input = readFile("Day1")
        for (i in 1 until input.size) {
            if (input[i] > input[i-1]) {
                count++
            }
        }
        return count
    }

    private fun solvePart2(): Int {
        return 0
    }
}
