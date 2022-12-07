package org.advent2022

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day5SupplyStacksTest {

    private val day5SupplyStacks = Day5SupplyStacks()
    private val inputFileName = "Day5Input.txt"

    private fun readResourceFile(): List<String> {
        val resourcesPath = "src/test/resources"
        return File("$resourcesPath/$inputFileName").readLines()
    }

    @Test
    fun rearrangeCrates() {
        val stacksTopItems = day5SupplyStacks.rearrangeCrates(readResourceFile())
        val expected = "PSNRGBTFT"

        assertEquals(expected, stacksTopItems)
    }

    @Test
    fun rearrangeCratesWithRetainedOrder() {
        val stacksTopItems = day5SupplyStacks.rearrangeCrates(readResourceFile(), true)
        val expected = "BNTZFPMMW"

        assertEquals(expected, stacksTopItems)
    }

}