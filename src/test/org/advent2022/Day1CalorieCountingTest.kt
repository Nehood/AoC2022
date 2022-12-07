package org.advent2022

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day1CalorieCountingTest {

    private val day1CalorieCounting = Day1CalorieCounting()
    private val inputFileName = "Day1Input.txt"

    private fun readResourceFile(): List<List<Int>> {
        val resourcesPath = "src/test/resources"
        val lines = File("$resourcesPath/$inputFileName").readLines()
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
        val fattestElfCalories = day1CalorieCounting.countCaloriesOfNOfFattestElves(readResourceFile())
        val expected = 72240

        assertEquals(expected, fattestElfCalories)
    }

    @Test
    fun countCaloriesOfFattest3Elves() {
        val fattestElvesCalories = day1CalorieCounting.countCaloriesOfNOfFattestElves(readResourceFile(), 3)
        val expected = 210957

        assertEquals(expected, fattestElvesCalories)
    }
}