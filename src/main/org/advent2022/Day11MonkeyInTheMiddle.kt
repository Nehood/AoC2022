package org.advent2022

import org.advent2022.data.Day11Monkey
import java.math.BigInteger

class Day11MonkeyInTheMiddle {
    fun calculateMonkeyBusinessLevel(notes: List<String>, rounds: Int = 20, relief: Int = 3): BigInteger {
        val monkeys = readNotes(notes)
        val masterDivisor = monkeys.map { it.testValue }.reduce { acc, i -> acc * i }
        for (round in 1..rounds) {
            for (monkey in monkeys) {
                monkey.play(monkeys, masterDivisor, relief)
            }
        }
        val inspections = monkeys.map { it.inspectionsCount }.sortedDescending().take(2)
        return inspections[0].times(inspections[1])
    }

    private fun readNotes(notes: List<String>): Array<Day11Monkey> {
        return notes.chunked(7).map { Day11Monkey.fromNote(it) }.toTypedArray()
    }
}