package org.advent2022

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day1CalorieCountingTest {

    private val day1CalorieCounting = Day1CalorieCounting()
    private val inputFileName = "Day1Input.txt"

    private fun readResourceFile(fileName: String): List<List<Int>> {
        val resourcesPath = "src/test/resources"
        val lines = File("$resourcesPath/$fileName").readLines()
        val listOfLists = mutableListOf<List<Int>>()
        val list = mutableListOf<Int>()
        for (line: String in lines) {
            when {
                line.isEmpty() -> {
                    listOfLists.add(list.toList())
                    list.clear()
                }

                else -> list.add(line.toInt())
            }
        }
        return listOfLists.toList()
    }

    @Test
    fun countCaloriesOfFattestElf() {
        val calories = readResourceFile(inputFileName)
        val fattestElfCalories = day1CalorieCounting.countCaloriesOfFattestElf(calories)
        val expected = 72240

        assertEquals(expected, fattestElfCalories)
    }

    @Test
    fun countCaloriesOfFattest3Elves() {
        val calories = readResourceFile(inputFileName)
        val fattestElvesCalories = day1CalorieCounting.countCaloriesOfNOfFattestElves(calories)
        val expected = 210957

        assertEquals(expected, fattestElvesCalories)
    }
}