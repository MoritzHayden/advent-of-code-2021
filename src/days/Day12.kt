package days

import readFile
import java.util.*

class Day12 {
    fun solveAll() {
        println("Solving day 12..")
        println("Part 1: ${solvePart1()}")
        println("Part 2: ${solvePart2()}")
    }

    private fun solvePart1(): Int {
        val input = readFile("day12")
        val edges = getEdges(input)
        val visitedNodes = mutableListOf<String>("START")
        val result = mutableListOf<MutableList<String>>()
        findAllPaths(mutableListOf("START"), "END", edges, visitedNodes, result)

        result.removeIf{ !it.contains("END") }

        return result.size
    }

    private fun solvePart2(): Int {
        return 0
    }

    private fun findAllPaths(
        currentPath: MutableList<String>,
        endNode: String,
        edges: List<Pair<String, String>>,
        visitedNodes: MutableList<String>,
        result: MutableList<MutableList<String>>
    ): List<List<String>> {
        val allPaths = mutableListOf<List<String>>()
        val nodesFromHere = getNextNodesFromNode(currentPath.last(), edges)
        nodesFromHere.removeIf { visitedNodes.contains(it) }
        allPaths.add(currentPath)
        result.add(currentPath)

        for (node in nodesFromHere) {
            val newPath = currentPath.toMutableList()
            newPath.add(node)
            if (node[0].isLowerCase()) visitedNodes.add(node)

            val tempPath = findAllPaths(newPath, endNode, edges, visitedNodes, result)
            if (tempPath.isNotEmpty()) allPaths.add(tempPath[0])
            visitedNodes.remove(node)
}

        return allPaths
    }

    private fun getEdges(input: List<String>): List<Pair<String, String>> {
        val edges = mutableListOf<Pair<String, String>>()

        input.forEach {
            var (from, to) = it.split("-")
            if (from == "start" || from == "end") from = from.uppercase()
            if (to == "start" || to == "end") to = to.uppercase()
            edges.add(Pair(from, to))
        }

        return edges
    }

    private fun getNextNodesFromNode(
        node: String,
        allEdges: List<Pair<String, String>>
    ): MutableList<String> {
        if (node == "END") return mutableListOf() //If end, return empty list

        val nextNodes = mutableListOf<String>()

        // Find all possibilities for the next node
        allEdges.forEach {
            if (it.first != node && it.second == node) nextNodes.add(it.first)
            else if (it.first == node && it.second != node) nextNodes.add(it.second)
        }

        // Remove the start node if we are not there
        if (node != "START") nextNodes.remove("START")

        return nextNodes
    }
}
