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
        val allFolds = getFolds(input)//.take(1) // Only one fold is needed for this part
        val sheet = getSheet(allPoints)
        val foldedSheet = foldSheet(sheet, allFolds)
        var totalDots = 0

        foldedSheet.forEach { sheetRow ->
            sheetRow.forEach {
                totalDots += it
            }
        }

        //852 too high
        return totalDots
    }

    private fun solvePart2(): Int {
        return 0
    }

    private fun foldSheet(
        sheet: MutableList<MutableList<Int>>,
        folds: List<Pair<Char, Int>>
    ): MutableList<MutableList<Int>> {
        var newSheet = sheet.toMutableList()

        for (fold in folds) {
            val position = fold.second
            when (fold.first) {
                'x' -> {
                    // Fold on the x-axis
                    val left = mutableListOf<MutableList<Int>>()
                    val right = mutableListOf<MutableList<Int>>()

                    for (i in newSheet.indices) {
                        val leftRow = mutableListOf<Int>()
                        val rightRow = mutableListOf<Int>()

                        for (j in newSheet[i].indices) {
                            if (j < position) {
                                leftRow.add(newSheet[i][j])
                            }
                            if (j > position) {
                                rightRow.add(newSheet[i][j])
                            }
                        }

                        left.add(leftRow)
                        right.add(rightRow)
                    }

                    newSheet.clear()

                    for (i in left.indices) {
                        val tempRow = mutableListOf<Int>()
                        var rightIndex = right[i].size - 1

                        for (j in left[i].indices) {
                            if (left[i][j] == 1 || right[i][rightIndex] == 1) {
                                tempRow.add(1)
                            } else {
                                tempRow.add(0)
                            }

                            if (rightIndex > 0) {
                                rightIndex--
                            }
                        }

                        newSheet.add(tempRow)
                    }
                }
                'y' -> {
                    // Fold on the y-axis
                    val top = mutableListOf<MutableList<Int>>()
                    val bottom = mutableListOf<MutableList<Int>>()

                    for (i in newSheet.indices) {
                        val row = newSheet[i]
                        if (i < position) {
                            top.add(row)
                        }
                        if (i > position) {
                            bottom.add(row)
                        }
                    }

                    newSheet.clear()

                    var bottomSize = bottom.size
                    for (i in top.indices) {
                        val tempRow = mutableListOf<Int>()
                        val topRow = top[i]
                        val bottomRow = bottom[bottomSize - i - 1]

                        for (j in topRow.indices) {
                            if (topRow[j] == 1 || bottomRow[j] == 1) {
                                tempRow.add(1)
                            } else {
                                tempRow.add(0)
                            }
                        }
                        newSheet.add(tempRow)
                    }
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
                val (x, y) = it.split(",")
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
