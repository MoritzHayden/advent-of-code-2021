package days

import readFile

class Day09 {
    fun solveAll() {
        println("Solving day 9...")
        println("Part 1: ${solvePart1()}")
        println("Part 2: ${solvePart2()}")
    }

    private fun solvePart1(): Int {
        val input = readFile("Day09")
        val caveHeights = getCaveHeights(input)
        val lowPoints = getLowPoints(caveHeights)

        return lowPoints.sum() + lowPoints.count()
    }

    private fun solvePart2(): Int {
        val input = readFile("Day09")
        val caveHeights = getCaveHeights(input)
        val lowPointCoordinates = getLowPointsCoordinates(caveHeights)
        val basins: MutableList<MutableList<Pair<Int, Int>>> = mutableListOf()
        for (point in lowPointCoordinates.indices) {
            // Get basin
            val currentBasin: MutableList<Pair<Int, Int>> = mutableListOf()
            currentBasin.add(lowPointCoordinates[point])
            var basinComplete = false

            for (i in 0..7) {
                val iterator = currentBasin.listIterator()
                while (iterator.hasNext() && !basinComplete) {
                    val point = iterator.next()
                    val adjacentMembers = getValidAdjacentMembers(caveHeights, point)
                    if (adjacentMembers.size == 0 && !iterator.hasNext()) {
                        basinComplete = true
                    } else {
                        for (point in adjacentMembers) {
                            if (!currentBasin.contains(point)) {
                                iterator.add(point)
                            }
                        }
                    }
                }
            }

            basins.add(currentBasin)
        }

        basins.sortBy { it.size }
        basins.reverse()

        return basins[0].size * basins[1].size * basins[2].size
    }

    private fun getBasin(
        caveHeights: MutableList<MutableList<Int>>,
        basinOrigin: Pair<Int, Int>
    ): MutableList<Pair<Int, Int>> {
        val basinMembers = mutableListOf<Pair<Int, Int>>()
        return basinMembers
    }

    private fun getValidAdjacentMembers(
        caveHeights: MutableList<MutableList<Int>>,
        point: Pair<Int, Int>
    ): MutableList<Pair<Int, Int>> {
        // Might build recursion around this
        val basinMembers = mutableListOf<Pair<Int, Int>>()

        if (point.first > 0) {
            if (caveHeights[point.second][point.first - 1] != 9) {
                // Get the left member if valid
                basinMembers.add(Pair(point.first - 1, point.second))
            }
        }

        if (point.first < caveHeights[0].size - 1) {
            if (caveHeights[point.second][point.first + 1] != 9) {
                // Get the right member if valid
                basinMembers.add(Pair(point.first + 1, point.second))
            }
        }

        if (point.second > 0) {
            if (caveHeights[point.second - 1][point.first] != 9) {
                // Get the top member if valid
                basinMembers.add(Pair(point.first, point.second - 1))
            }
        }

        if (point.second < caveHeights.size - 1) {
            if (caveHeights[point.second + 1][point.first] != 9) {
                // Get the bottom member if valid
                basinMembers.add(Pair(point.first, point.second + 1))
            }
        }

        return basinMembers
    }

    private fun getLowPoints(caveHeights: MutableList<MutableList<Int>>): MutableList<Int> {
        val lowPoints = mutableListOf<Int>()

        for (y in caveHeights.indices) {
            for (x in caveHeights[y].indices) {
                val currentNode = caveHeights[y][x]

                if (x == 0 && y == 0) {
                    // This is the top left corner node
                    if (currentNode < caveHeights[y + 1][x] &&
                        currentNode < caveHeights[y][x + 1]
                    ) {
                        lowPoints.add(currentNode)
                    }
                } else if (x == caveHeights[y].lastIndex && y == 0) {
                    // This is the top right corner node
                    if (currentNode < caveHeights[y][x - 1] &&
                        currentNode < caveHeights[y + 1][x]
                    ) {
                        lowPoints.add(currentNode)
                    }
                } else if (x == 0 && y == caveHeights.lastIndex) {
                    // This is the bottom left corner node
                    if (currentNode < caveHeights[y - 1][x] &&
                        currentNode < caveHeights[y][x + 1]
                    ) {
                        lowPoints.add(currentNode)
                    }
                } else if (x == caveHeights[y].lastIndex && y == caveHeights.lastIndex) {
                    // This is the bottom right corner node
                    if (currentNode < caveHeights[y - 1][x] &&
                        currentNode < caveHeights[y][x - 1]
                    ) {
                        lowPoints.add(currentNode)
                    }
                } else if (x == 0) {
                    // This is an edge node on the left (not corner)
                    if (currentNode < caveHeights[y - 1][x] &&
                        currentNode < caveHeights[y + 1][x] &&
                        currentNode < caveHeights[y][x + 1]
                    ) {
                        lowPoints.add(currentNode)
                    }

                } else if (x == caveHeights[y].lastIndex) {
                    // This is an edge node on the right (not corner)
                    if (currentNode < caveHeights[y - 1][x] &&
                        currentNode < caveHeights[y][x - 1] &&
                        currentNode < caveHeights[y + 1][x]
                    ) {
                        lowPoints.add(currentNode)
                    }
                } else if (y == 0) {
                    // This is an edge node on the top (not corner)
                    if (currentNode < caveHeights[y][x - 1] &&
                        currentNode < caveHeights[y + 1][x] &&
                        currentNode < caveHeights[y][x + 1]
                    ) {
                        lowPoints.add(currentNode)
                    }
                } else if (y == caveHeights.lastIndex) {
                    // This is an edge node on the bottom (not corner)
                    if (currentNode < caveHeights[y - 1][x] &&
                        currentNode < caveHeights[y][x - 1] &&
                        currentNode < caveHeights[y][x + 1]
                    ) {
                        lowPoints.add(currentNode)
                    }
                } else {
                    // This is a normal node
                    if (currentNode < caveHeights[y - 1][x] &&
                        currentNode < caveHeights[y][x - 1] &&
                        currentNode < caveHeights[y + 1][x] &&
                        currentNode < caveHeights[y][x + 1]
                    ) {
                        lowPoints.add(currentNode)
                    }
                }
            }
        }

        return lowPoints
    }

    private fun getLowPointsCoordinates(caveHeights: MutableList<MutableList<Int>>): MutableList<Pair<Int, Int>> {
        val lowPointsCoordinates = mutableListOf<Pair<Int, Int>>()

        for (y in caveHeights.indices) {
            for (x in caveHeights[y].indices) {
                val currentNode = caveHeights[y][x]
                val currentCoordinate = Pair(x, y)

                if (x == 0 && y == 0) {
                    // This is the top left corner node
                    if (currentNode < caveHeights[y + 1][x] &&
                        currentNode < caveHeights[y][x + 1]
                    ) {
                        lowPointsCoordinates.add(currentCoordinate)
                    }
                } else if (x == caveHeights[y].lastIndex && y == 0) {
                    // This is the top right corner node
                    if (currentNode < caveHeights[y][x - 1] &&
                        currentNode < caveHeights[y + 1][x]
                    ) {
                        lowPointsCoordinates.add(currentCoordinate)
                    }
                } else if (x == 0 && y == caveHeights.lastIndex) {
                    // This is the bottom left corner node
                    if (currentNode < caveHeights[y - 1][x] &&
                        currentNode < caveHeights[y][x + 1]
                    ) {
                        lowPointsCoordinates.add(currentCoordinate)
                    }
                } else if (x == caveHeights[y].lastIndex && y == caveHeights.lastIndex) {
                    // This is the bottom right corner node
                    if (currentNode < caveHeights[y - 1][x] &&
                        currentNode < caveHeights[y][x - 1]
                    ) {
                        lowPointsCoordinates.add(currentCoordinate)
                    }
                } else if (x == 0) {
                    // This is an edge node on the left (not corner)
                    if (currentNode < caveHeights[y - 1][x] &&
                        currentNode < caveHeights[y + 1][x] &&
                        currentNode < caveHeights[y][x + 1]
                    ) {
                        lowPointsCoordinates.add(currentCoordinate)
                    }

                } else if (x == caveHeights[y].lastIndex) {
                    // This is an edge node on the right (not corner)
                    if (currentNode < caveHeights[y - 1][x] &&
                        currentNode < caveHeights[y][x - 1] &&
                        currentNode < caveHeights[y + 1][x]
                    ) {
                        lowPointsCoordinates.add(currentCoordinate)
                    }
                } else if (y == 0) {
                    // This is an edge node on the top (not corner)
                    if (currentNode < caveHeights[y][x - 1] &&
                        currentNode < caveHeights[y + 1][x] &&
                        currentNode < caveHeights[y][x + 1]
                    ) {
                        lowPointsCoordinates.add(currentCoordinate)
                    }
                } else if (y == caveHeights.lastIndex) {
                    // This is an edge node on the bottom (not corner)
                    if (currentNode < caveHeights[y - 1][x] &&
                        currentNode < caveHeights[y][x - 1] &&
                        currentNode < caveHeights[y][x + 1]
                    ) {
                        lowPointsCoordinates.add(currentCoordinate)
                    }
                } else {
                    // This is a normal node
                    if (currentNode < caveHeights[y - 1][x] &&
                        currentNode < caveHeights[y][x - 1] &&
                        currentNode < caveHeights[y + 1][x] &&
                        currentNode < caveHeights[y][x + 1]
                    ) {
                        lowPointsCoordinates.add(currentCoordinate)
                    }
                }
            }
        }

        return lowPointsCoordinates
    }

    private fun getCaveHeights(input: List<String>): MutableList<MutableList<Int>> {
        val caveHeights = mutableListOf(mutableListOf<Int>())
        for (i in input) {
            caveHeights.add(i.split("").subList(1, i.length + 1).map { it.toInt() }.toMutableList())
        }
        caveHeights.removeAt(0)

        return caveHeights
    }
}
