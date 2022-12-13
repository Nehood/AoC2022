package org.advent2022

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day12HillClimbingAlgorithmTest {

    private val day12HillClimbingAlgorithm = Day12HillClimbingAlgorithm()
    private val inputFileName = "Day12Input.txt"

    private fun readResourceFile(): Array<CharArray> {
        val resourcesPath = "src/test/resources"
        return File("$resourcesPath/$inputFileName").readLines().map { it.toCharArray() }.toTypedArray()
    }

    @Test
    fun findShortestPathTest() {
        val input = """
            Sabqponm
            abcryxxl
            accszExk
            acctuvwj
            abdefghi
        """.trimIndent().lines().map { it.toCharArray() }.toTypedArray()

        val shortestPathLength = day12HillClimbingAlgorithm.findShortestPath(input)
        val expected = 31
        assertEquals(expected, shortestPathLength)
    }

    @Test
    fun findShortestPath() {
        val shortestPathLength = day12HillClimbingAlgorithm.findShortestPath(readResourceFile())
        val expected = 350
        assertEquals(expected, shortestPathLength)
    }

    @Test
    fun findShortestPathFromAnyGivenElevationTest() {
        val input = """
            Sabqponm
            abcryxxl
            accszExk
            acctuvwj
            abdefghi
        """.trimIndent().lines().map { it.toCharArray() }.toTypedArray()

        val shortestPathLength = day12HillClimbingAlgorithm.findShortestPathFromAnyGivenElevation(input)
        val expected = 29
        assertEquals(expected, shortestPathLength)
    }

    @Test
    fun findShortestPathFromAnyGivenElevation() {
        val shortestPathLength = day12HillClimbingAlgorithm.findShortestPathFromAnyGivenElevation(readResourceFile())
        val expected = 350
        assertEquals(expected, shortestPathLength)
    }
}