package org.advent2022.data

data class Day5Instruction(val itemsCount: Int, val from: Int, val to: Int) {
    companion object {
        val MOVE = "move "
        val FROM = "from "
        val TO = "to "
        fun parseInstruction(text: String): Day5Instruction {
            val move = text.substringAfter(MOVE).substringBefore(FROM).trim().toInt()
            val from = text.substringAfter(FROM).substringBefore(TO).trim().toInt()
            val to = text.substringAfter(TO).trim().toInt()
            return Day5Instruction(move, from, to)
        }
    }
}