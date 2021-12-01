import days.*
import java.lang.Exception
import java.util.Scanner

fun main () {
    while (true) {
        try {
            // Get user input
            val reader = Scanner(System.`in`)
            print("\nDay to run (0 to quit): ")

            // Run code for day
            when (reader.nextInt()) {
                0 -> {
                    println("Quitting...")
                    break
                }
                1 -> Day1().solveAll()
                2 -> Day2().solveAll()
                3 -> Day3().solveAll()
                4 -> Day4().solveAll()
                5 -> Day5().solveAll()
                6 -> Day6().solveAll()
                7 -> Day7().solveAll()
                8 -> Day8().solveAll()
                9 -> Day9().solveAll()
                10 -> Day10().solveAll()
                11 -> Day11().solveAll()
                12 -> Day12().solveAll()
                13 -> Day13().solveAll()
                14 -> Day14().solveAll()
                15 -> Day15().solveAll()
                16 -> Day16().solveAll()
                17 -> Day17().solveAll()
                18 -> Day18().solveAll()
                19 -> Day19().solveAll()
                20 -> Day20().solveAll()
                21 -> Day21().solveAll()
                22 -> Day22().solveAll()
                23 -> Day23().solveAll()
                24 -> Day24().solveAll()
                else -> println("Error: Day not found!")
            }
        } catch (ex: Exception) {
            println("Error: Invalid input!")
        }
    }
}
