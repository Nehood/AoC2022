package org.advent2022

import org.junit.jupiter.api.Test
import java.io.File
import java.math.BigInteger
import kotlin.test.assertEquals

class Day11MonkeyInTheMiddleTest {

    private val day11MonkeyInTheMiddle = Day11MonkeyInTheMiddle()
    private val inputFileName = "Day11Input.txt"

    private fun readResourceFile(): List<String> {
        val resourcesPath = "src/test/resources"
        return File("$resourcesPath/$inputFileName").readLines()
    }

    @Test
    fun calculateMonkeyBusinessLevelTest() {
        val input = """
            Monkey 0:
              Starting items: 79, 98
              Operation: new = old * 19
              Test: divisible by 23
                If true: throw to monkey 2
                If false: throw to monkey 3
            
            Monkey 1:
              Starting items: 54, 65, 75, 74
              Operation: new = old + 6
              Test: divisible by 19
                If true: throw to monkey 2
                If false: throw to monkey 0
            
            Monkey 2:
              Starting items: 79, 60, 97
              Operation: new = old * old
              Test: divisible by 13
                If true: throw to monkey 1
                If false: throw to monkey 3
            
            Monkey 3:
              Starting items: 74
              Operation: new = old + 3
              Test: divisible by 17
                If true: throw to monkey 0
                If false: throw to monkey 1
        """.trimIndent().lines()
        val monkeyBusinessLevel = day11MonkeyInTheMiddle.calculateMonkeyBusinessLevel(input)
        val expected = BigInteger.valueOf(10605)

        assertEquals(expected, monkeyBusinessLevel)
    }

    @Test
    fun calculateMonkeyBusinessLevel() {
        val monkeyBusinessLevel = day11MonkeyInTheMiddle.calculateMonkeyBusinessLevel(readResourceFile())
        val expected = BigInteger.valueOf(101436)

        assertEquals(expected, monkeyBusinessLevel)
    }

    @Test
    fun calculateMonkeyBusinessLevelWithoutReliefTest() {
        val input = """
            Monkey 0:
              Starting items: 79, 98
              Operation: new = old * 19
              Test: divisible by 23
                If true: throw to monkey 2
                If false: throw to monkey 3

            Monkey 1:
              Starting items: 54, 65, 75, 74
              Operation: new = old + 6
              Test: divisible by 19
                If true: throw to monkey 2
                If false: throw to monkey 0

            Monkey 2:
              Starting items: 79, 60, 97
              Operation: new = old * old
              Test: divisible by 13
                If true: throw to monkey 1
                If false: throw to monkey 3

            Monkey 3:
              Starting items: 74
              Operation: new = old + 3
              Test: divisible by 17
                If true: throw to monkey 0
                If false: throw to monkey 1
        """.trimIndent().lines()
        val monkeyBusinessLevel = day11MonkeyInTheMiddle.calculateMonkeyBusinessLevel(input, 10000, 1)
        val expected = BigInteger.valueOf(2713310158)

        assertEquals(expected, monkeyBusinessLevel)
    }

    @Test
    fun calculateMonkeyBusinessLevelWithoutRelief() {
        val monkeyBusinessLevel = day11MonkeyInTheMiddle.calculateMonkeyBusinessLevel(readResourceFile(), 10000, 1)
        val expected = BigInteger.valueOf(19754471646)

        assertEquals(expected, monkeyBusinessLevel)
    }
}