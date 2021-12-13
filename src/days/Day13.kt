package days

import readFile

class Day13 {
    fun solveAll() {
        println("Solving day 13...")
        println("Part 1: ${solvePart1()}")
        println("Part 2: ${solvePart2()}")
    }

    private fun solvePart1(): Int {
        val input = readFile("day13")
        val allPoints = getPoints(input)
        val allFolds = getFolds(input).take(1) // Only one fold is needed for this part
        val sheet = getSheet(allPoints)
        val foldedSheet = foldSheet(sheet, allFolds)
        var totalDots = 0

        allFolds.forEach(::println)

        foldedSheet.forEach { sheetRow ->
            sheetRow.forEach {
                totalDots += it
            }
        }

        return totalDots
    }

    private fun solvePart2(): Int {
        return 0
    }

    private fun foldSheet(sheet: MutableList<MutableList<Int>>, folds: List<Pair<Char, Int>>): MutableList<MutableList<Int>> {
        val newSheet = MutableList(sheet.size) { MutableList(sheet[0].size) { 0 } }

        for (fold in folds) {
            val position = fold.second
            when (fold.first) {
                'x' -> {
                    // Fold on the x-axis
                }
                'y' -> {
                    // Fold on the y-axis
                    val top = mutableListOf<MutableList<Int>>()
                    val bottom = mutableListOf<MutableList<Int>>()

                    for (i in sheet.indices) {
                        val row = sheet[i]
                        if (i < position) {
                            top.add(row)
                        }
                        if (i > position) {
                            bottom.add(row)
                        }
                    }

                    top.forEach(::println)
                    println()
                    bottom.forEach(::println)

                }
            }
        }

        return newSheet
    }

    private fun getSheet(allPoints: List<Pair<Int, Int>>): MutableList<MutableList<Int>> {
        val sheet = mutableListOf<MutableList<Int>>()
        var maxX = 0
        var maxY = 0

        allPoints.forEach {
            if (it.first > maxX) maxX = it.first
            if (it.second > maxY) maxY = it.second
        }

        for (y in 0..maxY) {
            val row = mutableListOf<Int>()

            for (x in 0..maxX) {
                var currentPoint = Pair(x, y)
                if (allPoints.contains(currentPoint)) {
                    row.add(1)
                } else {
                    row.add(0)
                }
            }

            sheet.add(row)
        }
        return sheet
    }

    private fun getPoints(input: List<String>): List<Pair<Int, Int>> {
        val allPoints = mutableListOf<Pair<Int, Int>>()

        input.forEach {
            try {
                val(x, y) = it.split(",")
                allPoints.add(Pair(x.toInt(), y.toInt()))
            } catch (e: Exception) {
                // Ignore: we are only interested in the points
            }
        }

        return allPoints
    }

    private fun getFolds(input: List<String>): List<Pair<Char, Int>> {
        val allFolds = mutableListOf<Pair<Char, Int>>()

        input.forEach {
            try {
                val line = it.split(" ")
                val (axis, point) = line[2].split("=")
                allFolds.add(Pair(axis[0], point.toInt()))
            } catch (e: Exception) {
                // Ignore: we are only interested in the folds
            }
        }

        return allFolds
    }
}
