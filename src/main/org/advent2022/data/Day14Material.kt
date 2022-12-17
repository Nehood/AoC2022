package org.advent2022.data

enum class Day14Material(private val symbol: Char) {
    ROCK('#'),
    AIR('.'),
    SAND('o'),
    SOURCE('+');

    override fun toString(): String {
        return symbol.toString()
    }
}