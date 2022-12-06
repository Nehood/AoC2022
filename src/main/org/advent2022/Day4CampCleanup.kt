package org.advent2022

class Day4CampCleanup {
    fun findFullyContainedAssignments(assignments: List<Pair<IntRange, IntRange>>): Int {
        return assignments.count { it.containsItself() }
    }

    private fun Pair<IntRange, IntRange>.containsItself(): Boolean {
        return this.first.first <= this.second.first && this.first.last >= this.second.last || this.second.first <= this.first.first && this.second.last >= this.first.last
    }

    fun findOverlappingAssignments(assignments: List<Pair<IntRange, IntRange>>): Int {
        return assignments.count { it.overlaps() }
    }

    private fun Pair<IntRange, IntRange>.overlaps(): Boolean {
        return this.first.intersect(this.second).isNotEmpty()
    }
}