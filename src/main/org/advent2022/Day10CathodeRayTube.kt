package org.advent2022

import org.advent2022.data.Day10TimedInstruction
import kotlin.math.abs

class Day10CathodeRayTube {
    fun drawSprite(instructions: List<String>): String {
        var registerX = 1
        val timedInstructions = mutableListOf<Day10TimedInstruction>()

        var cycle = 1
        var crtOutput = ""
        instructions.forEach { instruction ->
            val instructionSplitted = instruction.split(' ')
            when (instructionSplitted[0]) {
                "noop" -> {
                    crtOutput += drawSprite(cycle - 1, registerX)
                    registerX += timedInstructions.executeInstructionsForGivenCycle(++cycle)
                }

                "addx" -> {
                    timedInstructions.add(Day10TimedInstruction(instructionSplitted[1].toInt(), cycle + 2))
                    crtOutput += drawSprite(cycle - 1, registerX)
                    registerX += timedInstructions.executeInstructionsForGivenCycle(++cycle)
                    crtOutput += drawSprite(cycle - 1, registerX)
                    registerX += timedInstructions.executeInstructionsForGivenCycle(++cycle)
                }

                else -> throw Exception("Unknown instruction!")
            }
        }
        crtOutput.chunked(40).forEach { println(it) }
        return crtOutput
    }

    private fun drawSprite(cycle: Int, registerX: Int): Char {
        return if (abs((cycle % 40) - registerX) <= 1) '#' else '.'
    }

    fun sumSixSignalsStrength(instructions: List<String>): Int {
        var registerX = 1
        val timedInstructions = mutableListOf<Day10TimedInstruction>()

        var sumOfSixSignalsStrength = 0
        var cycle = 1
        instructions.forEach { instruction ->
            val instructionSplitted = instruction.split(' ')
            when (instructionSplitted[0]) {
                "noop" -> {
                    registerX += timedInstructions.executeInstructionsForGivenCycle(++cycle)
                    if (cycleForModification(cycle)) sumOfSixSignalsStrength += cycle * registerX
                }

                "addx" -> {
                    timedInstructions.add(Day10TimedInstruction(instructionSplitted[1].toInt(), cycle + 2))
                    registerX += timedInstructions.executeInstructionsForGivenCycle(++cycle)
                    if (cycleForModification(cycle)) sumOfSixSignalsStrength += cycle * registerX
                    registerX += timedInstructions.executeInstructionsForGivenCycle(++cycle)
                    if (cycleForModification(cycle)) sumOfSixSignalsStrength += cycle * registerX
                }

                else -> throw Exception("Unknown instruction!")
            }
        }
        return sumOfSixSignalsStrength
    }

    private fun MutableList<Day10TimedInstruction>.executeInstructionsForGivenCycle(cycle: Int): Int {
        return this.filter { it.endCycle == cycle }.sumOf { it.value }
    }

    private fun cycleForModification(cycle: Int): Boolean {
        return cycle <= 220 && (cycle + 20) % 40 == 0
    }
}