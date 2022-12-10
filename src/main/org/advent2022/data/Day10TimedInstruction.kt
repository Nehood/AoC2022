package org.advent2022.data

data class Day10TimedInstruction(val value: Int, val endCycle: Int){
    override fun toString(): String {
        return "Day10TimedInstruction(value=$value, endCycle=$endCycle)"
    }
}
