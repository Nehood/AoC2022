package org.advent2022

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day15BeaconExclusionZoneTest {

    private val day15BeaconExclusionZone = Day15BeaconExclusionZone()
    private val inputFileName = "Day15Input.txt"

    private fun readResourceFile(): List<String> {
        val resourcesPath = "src/test/resources"
        return File("$resourcesPath/$inputFileName").readLines()
    }

    @Test
    fun countBlockedPositionsInGivenRowTest() {
        val input = """
            Sensor at x=2, y=18: closest beacon is at x=-2, y=15
            Sensor at x=9, y=16: closest beacon is at x=10, y=16
            Sensor at x=13, y=2: closest beacon is at x=15, y=3
            Sensor at x=12, y=14: closest beacon is at x=10, y=16
            Sensor at x=10, y=20: closest beacon is at x=10, y=16
            Sensor at x=14, y=17: closest beacon is at x=10, y=16
            Sensor at x=8, y=7: closest beacon is at x=2, y=10
            Sensor at x=2, y=0: closest beacon is at x=2, y=10
            Sensor at x=0, y=11: closest beacon is at x=2, y=10
            Sensor at x=20, y=14: closest beacon is at x=25, y=17
            Sensor at x=17, y=20: closest beacon is at x=21, y=22
            Sensor at x=16, y=7: closest beacon is at x=15, y=3
            Sensor at x=14, y=3: closest beacon is at x=15, y=3
            Sensor at x=20, y=1: closest beacon is at x=15, y=3
        """.trimIndent().lines()
        val row = 10
        val blockedPositionsCount = day15BeaconExclusionZone.countBlockedPositionsInGivenRow(input, row)
        val expected = 26

        assertEquals(expected, blockedPositionsCount)
    }

    @Test
    fun countBlockedPositionsInGivenRow() {
        val row = 2000000
        val blockedPositionsCount = day15BeaconExclusionZone.countBlockedPositionsInGivenRow(readResourceFile(), row)
        val expected = 5564017
        assertEquals(expected, blockedPositionsCount)
    }

    @Test
    fun findDistressBeaconTest() {
        val input = """
            Sensor at x=2, y=18: closest beacon is at x=-2, y=15
            Sensor at x=9, y=16: closest beacon is at x=10, y=16
            Sensor at x=13, y=2: closest beacon is at x=15, y=3
            Sensor at x=12, y=14: closest beacon is at x=10, y=16
            Sensor at x=10, y=20: closest beacon is at x=10, y=16
            Sensor at x=14, y=17: closest beacon is at x=10, y=16
            Sensor at x=8, y=7: closest beacon is at x=2, y=10
            Sensor at x=2, y=0: closest beacon is at x=2, y=10
            Sensor at x=0, y=11: closest beacon is at x=2, y=10
            Sensor at x=20, y=14: closest beacon is at x=25, y=17
            Sensor at x=17, y=20: closest beacon is at x=21, y=22
            Sensor at x=16, y=7: closest beacon is at x=15, y=3
            Sensor at x=14, y=3: closest beacon is at x=15, y=3
            Sensor at x=20, y=1: closest beacon is at x=15, y=3
        """.trimIndent().lines()
        val maxRow = 20
        val blockedPositionsCount = day15BeaconExclusionZone.findDistressBeacon(input, maxRow)
        val expected = 56000011L

        assertEquals(expected, blockedPositionsCount)
    }

    @Test
    fun findDistressBeacon() {
        val maxRow = 4000000
        val blockedPositionsCount = day15BeaconExclusionZone.findDistressBeacon(readResourceFile(), maxRow)
        val expected = 11558423398893L
        assertEquals(expected, blockedPositionsCount)
    }
}