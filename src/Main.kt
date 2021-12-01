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
                1 -> {
                    println("Running day 1...")
                    var currentDay = Day1()
                    currentDay.solveAll()
                }
                else -> println("Error: Day not found!")
            }
        } catch (ex: Exception) {
            println("Error: Invalid input!")
        }
    }
}
