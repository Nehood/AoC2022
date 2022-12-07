package org.advent2022

class Day6TuningTrouble {
    fun findFirstStartOfPacketMarker(buffer: String, messageModifier: Int = 4): Int {
        for (index in buffer.indices) {
            val packetSet = buffer.substring(index, index + messageModifier).toSet()
            if (packetSet.size == messageModifier) return index + messageModifier
        }
        return -1
    }
}