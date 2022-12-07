package org.advent2022

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day3RucksackReorganizationTest {

    private val day3RucksackReorganization = Day3RucksackReorganization()
    private val inputFileName = "Day3Input.txt"

    private fun readResourceFile(): List<String> {
        val resourcesPath = "src/test/resources"
        return File("$resourcesPath/$inputFileName").readLines()
    }

    @Test
    fun sumPriorityOfMisalignedItemsTest() {
        val input = """vJrwpWtwJgWrhcsFMMfFFhFp
                                jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                                PmmdzqPrVvPwwTWBwg
                                wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                                ttgJtRGJQctTZtZT
                                CrZsJsPPZsGzwwsLwLmpwMDw""".split("\n").map { it.trim() }
        val prioritySummed = day3RucksackReorganization.sumPriorityOfMisalignedItems(input)
        val expected = 157

        assertEquals(expected, prioritySummed)
    }

    @Test
    fun sumPriorityOfMisalignedItems() {
        val prioritySummed = day3RucksackReorganization.sumPriorityOfMisalignedItems(readResourceFile())
        val expected = 7811

        assertEquals(expected, prioritySummed)
    }

    @Test
    fun sumPriorityOfGroupBadgesTest() {
        val input = """vJrwpWtwJgWrhcsFMMfFFhFp
                                jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                                PmmdzqPrVvPwwTWBwg
                                wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                                ttgJtRGJQctTZtZT
                                CrZsJsPPZsGzwwsLwLmpwMDw""".split("\n").map { it.trim() }
        val badgesPrioritySummed = day3RucksackReorganization.sumPriorityOfEachGroupBadges(input)
        val expected = 70

        assertEquals(expected, badgesPrioritySummed)
    }

    @Test
    fun sumPriorityOfGroupBadges() {
        val prioritySummed = day3RucksackReorganization.sumPriorityOfEachGroupBadges(readResourceFile())
        val expected = 2639

        assertEquals(expected, prioritySummed)
    }
}