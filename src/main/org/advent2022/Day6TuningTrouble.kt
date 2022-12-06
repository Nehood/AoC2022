package org.advent2022

import org.advent2022.data.Day5Instruction

class Day6TuningTrouble {
    fun findFirstStartOfPacketMarker(buffer: String, messageModifier: Int = 0): Int {
        for (index in buffer.indices) {
            val packetSet = buffer.substring(index, index + 4 + messageModifier).toSet()
            if (packetSet.size == 4 + messageModifier) return index + 4 + messageModifier
        }
        return -1
    }
}