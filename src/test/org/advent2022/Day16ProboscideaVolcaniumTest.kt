package org.advent2022

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day16ProboscideaVolcaniumTest {

    private val day16ProboscideaVolcanium = Day16ProboscideaVolcanium()
    private val inputFileName = "Day16Input.txt"

    private fun readResourceFile(): List<String> {
        val resourcesPath = "src/test/resources"
        return File("$resourcesPath/$inputFileName").readLines()
    }
    @Test
    fun releaseMostPressureTest() {
        val input = """
            Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
            Valve BB has flow rate=13; tunnels lead to valves CC, AA
            Valve CC has flow rate=2; tunnels lead to valves DD, BB
            Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE
            Valve EE has flow rate=3; tunnels lead to valves FF, DD
            Valve FF has flow rate=0; tunnels lead to valves EE, GG
            Valve GG has flow rate=0; tunnels lead to valves FF, HH
            Valve HH has flow rate=22; tunnel leads to valve GG
            Valve II has flow rate=0; tunnels lead to valves AA, JJ
            Valve JJ has flow rate=21; tunnel leads to valve II
        """.trimIndent().lines()
        val mostPressureReleased = day16ProboscideaVolcanium.releaseMostPressure(input)
        val expected = 1651

        assertEquals(expected, mostPressureReleased)
    }

    @Test
    fun releaseMostPressure() {
        val mostPressureReleased = day16ProboscideaVolcanium.releaseMostPressure(readResourceFile())
        val expected = 2029

        assertEquals(expected, mostPressureReleased)
    }
}