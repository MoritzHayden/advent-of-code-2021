package days

import readFile

class Day04 {
    fun solveAll() {
        println("Solving day 4...")
        println("Part 1: ${solvePart1()}")
        println("Part 2: ${solvePart2()}")
    }

    private fun solvePart1(): Int {
        val input = readFile("Day04")
        val drawnNumbers = getBingoNumbers(input)
        val bingoBoards = getBingoBoards(input)
        val scoreBoards = getScoreBoards(bingoBoards.size)

        for (i in drawnNumbers) {
            // Mark the boards
            for (j in bingoBoards.indices) {
                for (k in bingoBoards[j].indices) {
                    for (l in bingoBoards[j][k].indices) {
                        if (bingoBoards[j][k][l] == i) {
                            scoreBoards[j][k][l] = 1
                        }
                    }
                }

                // Check for a winner
                if (isBingoWinner(scoreBoards[j])) {
                    return scoreBingoBoard(bingoBoards[j], scoreBoards[j], i)
                }
            }
        }

        return 0
    }

    private fun solvePart2(): Int {
        val input = readFile("Day04")
        val drawnNumbers = getBingoNumbers(input)
        val bingoBoards = getBingoBoards(input)
        val scoreBoards = getScoreBoards(bingoBoards.size)
        var scoreCount = 0
        var lastBoardIndex = 0

        for (i in drawnNumbers.indices) {
            // Mark the boards
            for (j in bingoBoards.indices) {
                for (k in bingoBoards[j].indices) {
                    for (l in bingoBoards[j][k].indices) {
                        if (bingoBoards[j][k][l] == drawnNumbers[i]) {
                            scoreBoards[j][k][l] = 1
                        }
                    }
                }
            }

            // Determine last card to win
            scoreCount = 0
            for (j in scoreBoards.indices) {
                if (isBingoWinner(scoreBoards[j])) {
                    scoreCount++
                } else {
                    lastBoardIndex = j
                }
            }

            // Check score of last card when it wins
            if (isBingoWinner(scoreBoards[lastBoardIndex])) {
                return scoreBingoBoard(bingoBoards[lastBoardIndex], scoreBoards[lastBoardIndex], drawnNumbers[i])
            }
        }

        return 0
    }

    private fun getBingoNumbers(input: List<String>): List<Int> {
        return input[0].split((",")).map { it.toInt() }
    }

    private fun getBingoBoards(input: List<String>): MutableList<List<List<Int>>> {
        val bingoBoards = mutableListOf<List<List<Int>>>()
        for (i in 2 until input.size step 6) {
            val bingoRow1 = input[i].trim().split(" +".toRegex()).map { it.toInt() }
            val bingoRow2 = input[i + 1].trim().split(" +".toRegex()).map { it.toInt() }
            val bingoRow3 = input[i + 2].trim().split(" +".toRegex()).map { it.toInt() }
            val bingoRow4 = input[i + 3].trim().split(" +".toRegex()).map { it.toInt() }
            val bingoRow5 = input[i + 4].trim().split(" +".toRegex()).map { it.toInt() }
            val bingoBoard = listOf(bingoRow1, bingoRow2, bingoRow3, bingoRow4, bingoRow5)
            bingoBoards.add(bingoBoard)
        }
        return bingoBoards
    }

    private fun getScoreBoards(size: Int): MutableList<MutableList<MutableList<Int>>> {

        val scoreBoards: MutableList<MutableList<MutableList<Int>>> = mutableListOf()

        for (i in 0 until size) {
            val emptyRow1 = mutableListOf(0, 0, 0, 0, 0)
            val emptyRow2 = mutableListOf(0, 0, 0, 0, 0)
            val emptyRow3 = mutableListOf(0, 0, 0, 0, 0)
            val emptyRow4 = mutableListOf(0, 0, 0, 0, 0)
            val emptyRow5 = mutableListOf(0, 0, 0, 0, 0)
            val emptyBoard = mutableListOf(emptyRow1, emptyRow2, emptyRow3, emptyRow4, emptyRow5)
            scoreBoards.add(emptyBoard.toList().toMutableList())
        }
        return scoreBoards
    }

    private fun isBingoWinner(scoreBoard: List<List<Int>>): Boolean {
        for (i in scoreBoard.indices) {
            // Row winner
            if (scoreBoard[i][0] == 1 && scoreBoard[i][1] == 1 && scoreBoard[i][2] == 1 && scoreBoard[i][3] == 1 && scoreBoard[i][4] == 1) {
                return true
            }
            // Column winner
            if (scoreBoard[0][i] == 1 && scoreBoard[1][i] == 1 && scoreBoard[2][i] == 1 && scoreBoard[3][i] == 1 && scoreBoard[4][i] == 1) {
                return true
            }
        }
        return false
    }

    private fun scoreBingoBoard(bingoBoard: List<List<Int>>, scoreBoard: List<List<Int>>, lastNumber: Int): Int {
        var sumCard = 0

        for (i in scoreBoard.indices) {
            for (j in scoreBoard[i].indices) {
                if (scoreBoard[i][j] == 0) {
                    sumCard += bingoBoard[i][j]
                }
            }
        }

        return sumCard * lastNumber
    }
}
