package org.advent2022

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

internal class Day9RopeBridgeTest {
    private val day9RopeBridge = Day9RopeBridge()
    private val inputFileName = "Day9Input.txt"

    private fun readResourceFile(): List<String> {
        val resourcesPath = "src/test/resources"
        return File("$resourcesPath/$inputFileName").readLines()
    }

    @Test
    fun countTailVisitedPointsTest() {
        val input = """
            R 4
            U 4
            L 3
            D 1
            R 4
            D 1
            L 5
            R 2
        """.trimIndent().lines()

        val tailVisitedPointsCount = day9RopeBridge.countTailVisitedPoints(input)
        val expected = 13

        assertEquals(expected, tailVisitedPointsCount)
    }

    @Test
    fun countTailVisitedPoints() {
        val tailVisitedPointsCount = day9RopeBridge.countTailVisitedPoints(readResourceFile())
        val expected = 6498

        assertEquals(expected, tailVisitedPointsCount)
    }

    @Test
    fun count9KnotsVisitedPointsTestSimple() {
        val input = """
            R 4
            U 4
            L 3
            D 1
            R 4
            D 1
            L 5
            R 2
        """.trimIndent().lines()

        val tailVisitedPointsCount = day9RopeBridge.countNKnotsVisitedPoints(input)
        val expected = 1

        assertEquals(expected, tailVisitedPointsCount)
    }

    @Test
    fun count9KnotsVisitedPointsTest() {
        val input = """
            R 5
            U 8
            L 8
            D 3
            R 17
            D 10
            L 25
            U 20
        """.trimIndent().lines()

        val tailVisitedPointsCount = day9RopeBridge.countNKnotsVisitedPoints(input)
        val expected = 36

        assertEquals(expected, tailVisitedPointsCount)
    }

    @Test
    fun count9KnotsVisitedPoints() {
        val tailVisitedPointsCount = day9RopeBridge.countNKnotsVisitedPoints(readResourceFile())
        val expected = 2531

        assertEquals(expected, tailVisitedPointsCount)
    }
}