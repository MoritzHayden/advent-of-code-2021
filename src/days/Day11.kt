package days

import readFile

class Day11 {
    fun solveAll() {
        println("Solving day 11...")
        println("Part 1: ${solvePart1()}")
        println("Part 2: ${solvePart2()}")
    }

    private fun solvePart1(): Int {
        val input = readFile("day11")
        val squidGrid = getSquidGrid(input)
        val steps = 100
        var totalFlashes = 0

        for (i in 0 until steps) {
            stepFirst(squidGrid)

            val pointsFlashed = stepSecond(squidGrid)
            totalFlashes += pointsFlashed.size

            stepThird(squidGrid, pointsFlashed)
        }

        return totalFlashes
    }

    private fun solvePart2(): Int {
        val input = readFile("day11")
        val squidGrid = getSquidGrid(input)
        var simultaneousFlash = false
        var stepsTaken = 0

        while (!simultaneousFlash) {
            stepsTaken++
            stepFirst(squidGrid)
            val pointsFlashed = stepSecond(squidGrid)
            stepThird(squidGrid, pointsFlashed)
            simultaneousFlash = isSimultaneousFlash(squidGrid)
        }

        return stepsTaken
    }

    private fun isSimultaneousFlash(squidGrid: MutableList<MutableList<Int>>): Boolean {
        for (i in 1 until squidGrid.size - 1) {
            for (j in 1 until squidGrid[i].size - 1) {
                if (squidGrid[i][j] != squidGrid[1][1]) {
                    return false
                }
            }
        }

        return true
    }

    private fun stepFirst(squidGrid: MutableList<MutableList<Int>>) {
        for (y in 1 until squidGrid.size - 1) {
            for (x in 1 until squidGrid[y].size - 1) {
                squidGrid[y][x]++
            }
        }
    }

    private fun stepSecond(squidGrid: MutableList<MutableList<Int>>): MutableList<Pair<Int, Int>> {
        val pointsFlashed = mutableListOf<Pair<Int, Int>>()
        val pointsToFlash = mutableListOf<Pair<Int, Int>>()

        for (y in 1 until squidGrid.size - 1) {
            for (x in 1 until squidGrid[y].size - 1) {
                val currentPoint = Pair(x, y)

                if (squidGrid[currentPoint.second][currentPoint.first] > 9) {
                    pointsToFlash.add(currentPoint)
                }
            }
        }

        for (point in pointsToFlash) {
            flash(point, pointsFlashed, squidGrid)
        }

        return pointsFlashed
    }

    private fun stepThird(squidGrid: MutableList<MutableList<Int>>, pointsFlashed: MutableList<Pair<Int, Int>>) {
        pointsFlashed.forEach {
            squidGrid[it.second][it.first] = 0
        }
    }

    private fun flash(
        currentPoint: Pair<Int, Int>,
        pointsFlashed: MutableList<Pair<Int, Int>>,
        squidGrid: MutableList<MutableList<Int>>
    ) {
        if (!pointsFlashed.contains(currentPoint)) {
            //Cannot flash twice
            pointsFlashed.add(currentPoint)
            val pointsToIncrement = getAllValidAdjacent(currentPoint, squidGrid)

            // Increment all adjacent points by 1
            for (point in pointsToIncrement) {
                squidGrid[point.second][point.first]++
            }

            // Check if any adjacent points are ready to flash now
            for (point in pointsToIncrement) {
                if (squidGrid[point.second][point.first] > 9) {
                    flash(point, pointsFlashed, squidGrid)
                }
            }
        }
    }

    private fun getSquidGrid(input: List<String>): MutableList<MutableList<Int>> {
        val totalRows = input.size + 2
        val totalCols = input[0].length + 2
        val squidGrid = MutableList(totalRows) { MutableList(totalCols) { -1 } }

        for (i in input.indices) {
            val rowChars = input[i].toCharArray()
            for (j in rowChars.indices) {
                squidGrid[i + 1][j + 1] = rowChars[j].toString().toInt()
            }
        }

        return squidGrid
    }

    private fun getAllValidAdjacent(
        originPoint: Pair<Int, Int>,
        squidGrid: MutableList<MutableList<Int>>
    ): MutableList<Pair<Int, Int>> {
        val validAdjacentSquids = mutableListOf<Pair<Int, Int>>()

        val leftPoint = Pair(originPoint.first - 1, originPoint.second)
        val rightPoint = Pair(originPoint.first + 1, originPoint.second)
        val topPoint = Pair(originPoint.first, originPoint.second + 1)
        val bottomPoint = Pair(originPoint.first, originPoint.second - 1)
        val bottomLeftPoint = Pair(originPoint.first - 1, originPoint.second - 1)
        val topLeftPoint = Pair(originPoint.first - 1, originPoint.second + 1)
        val topRightPoint = Pair(originPoint.first + 1, originPoint.second + 1)
        val bottomRightPoint = Pair(originPoint.first + 1, originPoint.second - 1)

        if (isValidPoint(leftPoint, squidGrid)) validAdjacentSquids.add(leftPoint)
        if (isValidPoint(rightPoint, squidGrid)) validAdjacentSquids.add(rightPoint)
        if (isValidPoint(topPoint, squidGrid)) validAdjacentSquids.add(topPoint)
        if (isValidPoint(bottomPoint, squidGrid)) validAdjacentSquids.add(bottomPoint)
        if (isValidPoint(bottomLeftPoint, squidGrid)) validAdjacentSquids.add(bottomLeftPoint)
        if (isValidPoint(topLeftPoint, squidGrid)) validAdjacentSquids.add(topLeftPoint)
        if (isValidPoint(topRightPoint, squidGrid)) validAdjacentSquids.add(topRightPoint)
        if (isValidPoint(bottomRightPoint, squidGrid)) validAdjacentSquids.add(bottomRightPoint)

        return validAdjacentSquids
    }

    private fun isValidPoint(point: Pair<Int, Int>, squidGrid: MutableList<MutableList<Int>>): Boolean {
        return squidGrid[point.second][point.first] != -1
    }
}
