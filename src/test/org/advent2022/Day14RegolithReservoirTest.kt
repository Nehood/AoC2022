package org.advent2022

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day14RegolithReservoirTest {

    private val day14RegolithReservoir = Day14RegolithReservoir()
    private val inputFileName = "Day14Input.txt"

    private fun readResourceFile(): List<String> {
        val resourcesPath = "src/test/resources"
        return File("$resourcesPath/$inputFileName").readLines()
    }

    @Test
    fun countRestedSandUnitsWithFloorFalseTest() {
        val input = """
            498,4 -> 498,6 -> 496,6
            503,4 -> 502,4 -> 502,9 -> 494,9
        """.trimIndent().lines()
        val restedSandUnits = day14RegolithReservoir.countRestedSandUnitsWithFloor(input)
        val expected = 24

        assertEquals(expected, restedSandUnits)
    }
    @Test
    fun countRestedSandUnitsWithFloorFalse() {
        val restedSandUnits = day14RegolithReservoir.countRestedSandUnitsWithFloor(readResourceFile())
        val expected = 913

        assertEquals(expected, restedSandUnits)
    }


    @Test
    fun countRestedSandUnitsWithFloorTest() {
        val input = """
            498,4 -> 498,6 -> 496,6
            503,4 -> 502,4 -> 502,9 -> 494,9
        """.trimIndent().lines()
        val restedSandUnits = day14RegolithReservoir.countRestedSandUnitsWithFloor(input, true)
        val expected = 93

        assertEquals(expected, restedSandUnits)
    }

    @Test
    fun countRestedSandUnitsWithFloor() {
        val restedSandUnits = day14RegolithReservoir.countRestedSandUnitsWithFloor(readResourceFile(), true)
        val expected = 30762

        assertEquals(expected, restedSandUnits)
    }
}