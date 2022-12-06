package org.advent2022

import org.advent2022.data.Day5Instruction

class Day5SupplyStacks {

    val NO_ITEM = "    "

    fun rearrangeCrates(manifest: List<String>, retainOrder: Boolean = false): String {
        val splitIndex = manifest.indexOf("")
        val initialStacksState = manifest.subList(0, splitIndex)
        val stacks = prepareInitialStacksState(initialStacksState)
        val instructions = manifest.subList(splitIndex + 1, manifest.size).map { Day5Instruction.parseInstruction(it) }
        instructions.forEach { it.executeInstruction(stacks, retainOrder) }
        return stacks.map { it.last() }.joinToString().replace(", ", "")
    }

    private fun prepareInitialStacksState(initialStacksState: List<String>): List<ArrayDeque<Char>> {
        val numberOfStacks = initialStacksState.last().trim().last().digitToInt()
        val levels = initialStacksState.dropLast(1).map { it.chunked(4) }
        val stacks = IntRange(0, numberOfStacks - 1).map { stackNumber ->
            levels.reversed().map { it[stackNumber] }.takeWhile { it != NO_ITEM }.map { it[1] }
        }
        return stacks.map {
            ArrayDeque(it)
        }
    }

    private fun Day5Instruction.executeInstruction(stacks: List<ArrayDeque<Char>>, retainOrder: Boolean = false) {
        if (retainOrder) {
            stacks[this.to - 1].addAll(stacks[this.from - 1].takeLast(this.itemsCount))
            repeat(this.itemsCount) {
                stacks[this.from - 1].removeLast()
            }
        } else {
            repeat(this.itemsCount) {
                if (stacks[this.from - 1].isNotEmpty()) stacks[this.to - 1].addLast(stacks[this.from - 1].removeLast())
            }
        }
    }


}