package org.advent2022

import org.advent2022.data.Day14Material
import org.advent2022.data.Day14Material.*
import org.advent2022.data.Day14Point
import kotlin.math.max
import kotlin.math.min

class Day14RegolithReservoir {
    fun countRestedSandUnits(scanTraces: List<String>): Int {
        val lines = scanTraces.generateLinesFromScan()
        val lowestX = lines.minOfOrNull { min(it.first.first, it.first.second) }!!
        val highestX = lines.maxOfOrNull { max(it.first.first, it.first.second) }!!
        val highestY = lines.maxOfOrNull { max(it.second.first, it.second.second) }!!
        val map = lines.generateMapFromLines(lowestX, highestX, highestY)
        map[0][500 - lowestX] = SOURCE
        map.draw()
        return map.simulate(highestX, highestY)
    }

    fun countRestedSandUnitsWithFloor(scanTraces: List<String>, withFloor: Boolean = false): Int {
        val lines = scanTraces.generateLinesFromScan()
        val lowestX = lines.minOfOrNull { min(it.first.first, it.first.second) }!!
        val highestX = lines.maxOfOrNull { max(it.first.first, it.first.second) }!!
        val highestY = lines.maxOfOrNull { max(it.second.first, it.second.second) }!!
        val map = lines.generateMapFromLines(lowestX, highestX, highestY, withFloor)
        if (withFloor) map[0][500] = SOURCE else map[0][500 - lowestX] = SOURCE
        //map.draw()
        return map.simulate(highestX, highestY, withFloor)
    }

    private fun List<String>.generateLinesFromScan(): List<Pair<Pair<Int, Int>, Pair<Int, Int>>> {
        return this.flatMap {
            val linePoints = it.split(" -> ")
            val lines = mutableListOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()   //lines will be in format <x1,x2><y1,y2>
            var previousX = linePoints[0].split(',')[0].toInt()
            var previousY = linePoints[0].split(',')[1].toInt()
            for (point in linePoints.indices - 0) {
                val currentX = linePoints[point].split(',')[0].toInt()
                var currentY = linePoints[point].split(',')[1].toInt()
                lines.add(Pair(Pair(previousX, currentX), Pair(previousY, currentY)))
                previousX = currentX
                previousY = currentY
            }
            lines.toList()
        }
    }

    private fun List<Pair<Pair<Int, Int>, Pair<Int, Int>>>.generateMapFromLines(lowestX: Int, highestX: Int, highestY: Int, withFloor: Boolean = false): Array<Array<Day14Material>> {
        val ys = if (withFloor) IntRange(0, highestY + 2) else IntRange(0, highestY)
        val map = ys.map { y ->
            if (withFloor) Array(1000) { AIR } else Array((highestX + 1) - lowestX) { AIR }
        }.toTypedArray()
        this.forEach { line ->
            for (y in min(line.second.first, line.second.second)..max(line.second.first, line.second.second)) {
                for (x in min(line.first.first, line.first.second)..max(line.first.first, line.first.second)) {
                    if (withFloor) map[y][x] = ROCK else map[y][x - lowestX] = ROCK
                }
            }
        }
        if (withFloor) for (x in map[highestY + 2].indices) map[highestY + 2][x] = ROCK
        return map
    }

    private fun Array<Array<Day14Material>>.draw() {
        this.forEachIndexed { y, column ->
            column.forEachIndexed { x, day14Material ->
                print(this[y][x])
            }
            println()
        }
    }

    private fun Array<Array<Day14Material>>.simulate(highestX: Int, highestY: Int, withFloor: Boolean = false): Int {
        val source = this.getSourceLocation()
        var restedSandUnits = 0
        var currentSandLocation = source.copy()
        try {
            while (true) {
                when (AIR) {
                    this[currentSandLocation.y + 1][currentSandLocation.x] -> {
                        if (this[currentSandLocation.y][currentSandLocation.x] != SOURCE) this[currentSandLocation.y][currentSandLocation.x] = AIR
                        currentSandLocation.y = currentSandLocation.y + 1
                        this[currentSandLocation.y][currentSandLocation.x] = SAND
                    }

                    this[currentSandLocation.y + 1][currentSandLocation.x - 1] -> {
                        if (this[currentSandLocation.y][currentSandLocation.x] != SOURCE) this[currentSandLocation.y][currentSandLocation.x] = AIR
                        currentSandLocation.y = currentSandLocation.y + 1
                        currentSandLocation.x = currentSandLocation.x - 1
                        this[currentSandLocation.y][currentSandLocation.x] = SAND
                    }

                    this[currentSandLocation.y + 1][currentSandLocation.x + 1] -> {
                        if (this[currentSandLocation.y][currentSandLocation.x] != SOURCE) this[currentSandLocation.y][currentSandLocation.x] = AIR
                        currentSandLocation.y = currentSandLocation.y + 1
                        currentSandLocation.x = currentSandLocation.x + 1
                        this[currentSandLocation.y][currentSandLocation.x] = SAND
                    }

                    else -> {
                        //this.draw()
                        if (withFloor && currentSandLocation == source) return ++restedSandUnits
                        ++restedSandUnits
                        currentSandLocation = source.copy()
                    }
                }
            }
        } catch (e: IndexOutOfBoundsException) {
            return restedSandUnits
        }
    }

    private fun Array<Array<Day14Material>>.getSourceLocation(): Day14Point {
        this[0].forEachIndexed { x, day14Material ->
            if (day14Material == SOURCE) return Day14Point(x, 0)
        }
        return Day14Point(0, 0)
    }
}