package org.advent2022

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Day15BeaconExclusionZone {
    fun countBlockedPositionsInGivenRow(sensorsAndBeaconPositions: List<String>, row: Int): Int {
        val sensors = sensorsAndBeaconPositions.mapSensors()
        val beacons = sensorsAndBeaconPositions.mapBeacons()
        val sensorsAndBeacons = sensors.mapIndexed { index, sensor -> Pair(sensor, beacons[index]) }
        val ranges = sensorsAndBeacons.filter { abs(it.first.second - row) <= it.first.calculateManhattanDistance(it.second) }.mapIndexed { index, sensorAndBeacon ->
                    val manhattanDistance = sensorAndBeacon.first.calculateManhattanDistance(sensorAndBeacon.second)
                    val distance = abs(sensorAndBeacon.first.second - row)
                    val blockedCount = (manhattanDistance - distance) * 2
                    IntRange(sensorAndBeacon.first.first - blockedCount / 2, sensorAndBeacon.first.first + blockedCount / 2)
                }

        val sensorsInRow = sensors.filter { it.second == row }.map { IntRange(it.first, it.first) }
        val beaconsInRow = beacons.filter { it.second == row }.map { IntRange(it.first, it.first) }

        val mergedRanges = ranges.merge(sensorsInRow, beaconsInRow)
        return mergedRanges.maxBy { it.last - it.first }.length()
    }

    fun findDistressBeacon(sensorsAndBeaconPositions: List<String>, maxRow: Int): Long {
        val sensors = sensorsAndBeaconPositions.mapSensors()
        val beacons = sensorsAndBeaconPositions.mapBeacons()
        val sensorsAndBeacons = sensors.mapIndexed { index, sensor -> Pair(sensor, beacons[index]) }
        for (row in 0..maxRow) {
            val ranges = sensorsAndBeacons.filter { abs(it.first.second - row) <= it.first.calculateManhattanDistance(it.second) }.map { sensorAndBeacon ->
                        val manhattanDistance = sensorAndBeacon.first.calculateManhattanDistance(sensorAndBeacon.second)
                        val distance = abs(sensorAndBeacon.first.second - row)
                        val blockedCount = (manhattanDistance - distance) * 2
                        IntRange(sensorAndBeacon.first.first - blockedCount / 2, sensorAndBeacon.first.first + blockedCount / 2)
                    }

            val sensorsInRow = sensors.filter { it.second == row }.map { IntRange(it.first, it.first) }
            val beaconsInRow = beacons.filter { it.second == row }.map { IntRange(it.first, it.first) }

            val mergedRanges = ranges.merge(sensorsInRow, beaconsInRow)
            if (mergedRanges.size > 1) {
                return (mergedRanges[0].last + 1).toLong() * 4000000L + row.toLong()
            }
        }
        return -1L
    }

    private fun List<String>.mapSensors(): List<Pair<Int, Int>> {
        return this.map {
            val parts = it.substringAfter("Sensor at x=").substringBefore(':').split(", y=")
            Pair(parts[0].toInt(), parts[1].toInt())
        }
    }

    private fun List<String>.mapBeacons(): List<Pair<Int, Int>> {
        return this.map {
            val parts = it.substringAfter("closest beacon is at x=").trim().split(", y=")
            Pair(parts[0].toInt(), parts[1].toInt())
        }
    }

    private fun Pair<Int, Int>.calculateManhattanDistance(other: Pair<Int, Int>): Int {
        return abs(this.first - other.first) + abs(this.second - other.second)
    }

    private fun IntRange.length(): Int {
        return last - first
    }

    private fun List<IntRange>.merge(vararg rangeLists: List<IntRange>): List<IntRange> {
        val mergedRanges = mutableListOf<IntRange>()
        mergedRanges.addAll(this)
        for (ranges in rangeLists) {
            mergedRanges.addAll(ranges)
        }

        var mergePossible = true
        while (mergePossible) {
            mergePossible = false
            if (mergedRanges.size > 1) {
                for (index in 0..mergedRanges.lastIndex) {
                    for (newIndex in index + 1..mergedRanges.lastIndex) {
                        if (mergedRanges[index].canBeMerged(mergedRanges[newIndex])) {
                            mergedRanges[index] = IntRange(min(mergedRanges[index].first, mergedRanges[newIndex].first), max(mergedRanges[index].last, mergedRanges[newIndex].last))
                            mergedRanges.removeAt(newIndex)
                            mergePossible = true
                            break
                        }
                    }
                    if (mergePossible) break
                }
            }
        }
        return mergedRanges.sortedBy { it.first }.toList()
    }

    private fun IntRange.canBeMerged(other: IntRange): Boolean {
        return (other.first >= this.first && other.first <= this.last) || (this.first >= other.first && this.first <= other.last)
                || (other.last <= this.last && other.last >= this.first) || (this.last <= other.last && this.last >= other.first)
    }
}