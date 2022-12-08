package org.advent2022

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day8TreetopTreeHouseTest {
    private val day8TreetopTreeHouse = Day8TreetopTreeHouse()
    private val inputFileName = "Day8Input.txt"

    private fun readResourceFile(): Array<Array<Int>> {
        val resourcesPath = "src/test/resources"
        return File("$resourcesPath/$inputFileName").readLines().transformInput()
    }

    private fun List<String>.transformInput(): Array<Array<Int>> {
        return this.map { it.map { character -> character.digitToInt() }.toTypedArray() }.toTypedArray()
    }

    @Test
    fun countTreesVisibleFromOutsideTest() {
        val input = """
            30373
            25512
            65332
            33549
            35390
        """.trimIndent().lines().transformInput()
        val visibleTress = day8TreetopTreeHouse.countTreesVisibleFromOutside(input)
        val expected = 21

        assertEquals(expected, visibleTress)
    }

    @Test
    fun countTreesVisibleFromOutside() {
        val visibleTress = day8TreetopTreeHouse.countTreesVisibleFromOutside(readResourceFile())
        val expected = 1695

        assertEquals(expected, visibleTress)
    }

    @Test
    fun findHighestScenicScoreForTreeHouseTest() {
        val input = """
            30373
            25512
            65332
            33549
            35390
        """.trimIndent().lines().transformInput()
        val visibleTress = day8TreetopTreeHouse.findHighestScenicScoreForTreeHouse(input)
        val expected = 8

        assertEquals(expected, visibleTress)
    }

    @Test
    fun findHighestScenicScoreForTreeHouse() {
        val visibleTress = day8TreetopTreeHouse.findHighestScenicScoreForTreeHouse(readResourceFile())
        val expected = 287040

        assertEquals(expected, visibleTress)
    }
}