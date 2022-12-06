package org.advent2022

class Day3RucksackReorganization {
    fun sumPriorityOfMisalignedItems(items: List<String>): Int {
        val itemsSplitted = items.map { it.splitInHalf() }
        val commonItems = itemsSplitted.flatMap {
            it.first.intersect(it.second.toSet())
        }
        return commonItems.map { it.calculatePriority() }.sum()
    }

    fun sumPriorityOfEachGroupBadges(items: List<String>, groupSize: Int = 3): Int {
        val groups = items.chunked(groupSize)
        return groups.sumOf { it.findBadge().calculatePriority() }
    }

    private fun String.splitInHalf(): Pair<CharArray, CharArray> {
        val halfIndex = this.length / 2
        val half = this.substring(0, halfIndex).toCharArray()
        val secondHalf = this.substring(halfIndex, this.length).toCharArray()
        return Pair(half, secondHalf)
    }

    private fun Char.calculatePriority(): Int {
        return when (this) {
            lowercaseChar() -> {
                return this.code - 96
            }

            uppercaseChar() -> {
                return this.code - 38
            }

            else -> throw Exception("Not a letter!")

        }
    }

    private fun List<String>.findBadge(): Char {
        val sets = this.map { it.toSet() }
        return sets.reduce { acc, chars -> acc.intersect(chars) }.first()
    }
}