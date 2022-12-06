package org.advent2022

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class Day4CampCleanupTest {

    private val day4CampCleanup = Day4CampCleanup()
    private val inputFileName = "Day4Input.txt"

    private fun readResourceFile(): List<Pair<IntRange, IntRange>> {
        val resourcesPath = "src/test/resources"
        val lines = File("$resourcesPath/$inputFileName").readLines()
        return parseLines(lines)
    }

    private fun parseLines(lines: List<String>): List<Pair<IntRange, IntRange>> {
        val ranges = lines.map { it.split(',') }
        return ranges.map {
            val assignment1 = IntRange(it[0].substringBefore('-').toInt(), it[0].substringAfter('-').toInt())
            val assignment2 = IntRange(it[1].substringBefore('-').toInt(), it[1].substringAfter('-').toInt())
            Pair(assignment1, assignment2)
        }
    }

    @Test
    fun findFullyContainedAssignmentsTest(){
        val input = """
            2-4,6-8
            2-3,4-5
            5-7,7-9
            2-8,3-7
            6-6,4-6
            2-6,4-8
        """.trimIndent().split("\n")
        val countOfFullyContainedAssignmentPairs = day4CampCleanup.findFullyContainedAssignments(parseLines(input))
        val expected = 2

        Assertions.assertEquals(expected, countOfFullyContainedAssignmentPairs)
    }

    @Test
    fun findFullyContainedAssignments(){
        val countOfFullyContainedAssignmentPairs = day4CampCleanup.findFullyContainedAssignments(readResourceFile())
        val expected = 503

        Assertions.assertEquals(expected, countOfFullyContainedAssignmentPairs)
    }

    @Test
    fun findOverlappingAssignmentsTest(){
        val input = """
            2-4,6-8
            2-3,4-5
            5-7,7-9
            2-8,3-7
            6-6,4-6
            2-6,4-8
        """.trimIndent().split("\n")
        val countOfFullyContainedAssignmentPairs = day4CampCleanup.findOverlappingAssignments(parseLines(input))
        val expected = 4

        Assertions.assertEquals(expected, countOfFullyContainedAssignmentPairs)
    }

    @Test
    fun findOverlappingAssignments(){
        val countOfFullyContainedAssignmentPairs = day4CampCleanup.findOverlappingAssignments(readResourceFile())
        val expected = 827

        Assertions.assertEquals(expected, countOfFullyContainedAssignmentPairs)
    }
}