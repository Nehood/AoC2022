package org.advent2022

import org.advent2022.data.Day12Point

class Day12HillClimbingAlgorithm {

    private val START = 'S'
    private val END = 'E'

    fun findShortestPath(map: Array<CharArray>): Int {
        val startingPoint = map.findPoint(START)
        val endingPoint = map.findPoint(END)
        map[startingPoint.y][startingPoint.x] = 'a'
        map[endingPoint.y][endingPoint.x] = 'z'
        return map.Dijkstra(startingPoint, endingPoint)[endingPoint]!!
    }

    fun findShortestPathFromAnyGivenElevation(map: Array<CharArray>, elevation: Char = 'a'): Int {
        val startingPoint = map.findPoint(START)
        val endingPoint = map.findPoint(END)
        map[startingPoint.y][startingPoint.x] = 'a'
        map[endingPoint.y][endingPoint.x] = 'z'
        val allPointsOfGivenElevation = map.findAllPoints(elevation)
        val allPathsToTarget = map.Dijkstra(endingPoint, startingPoint, true)
        val pointsWithPaths = allPointsOfGivenElevation.intersect(allPathsToTarget.keys)
        return allPathsToTarget.filter { pointsWithPaths.contains(it.key) }.minBy { it.value }.value
    }

    // i will keep this, as a token of my failure
//    private fun Array<CharArray>.findShortestPath(startingPoint: Day12Point, endingPoint: Day12Point): Int {
//        val shortestPath = this.findShortestPathHelper(startingPoint.x, startingPoint.y, endingPoint, emptySet())
//        return shortestPath.size - 1
//    }
//
//    private fun Array<CharArray>.findShortestPathHelper(curX: Int, curY: Int, endingPoint: Day12Point, visitedPoints: Set<Day12Point>): Set<Day12Point> {
//        val currentlyVisitedPoints = visitedPoints.toMutableSet()
//        val currentPoint = Day12Point(curX, curY)
//        //this.printPath(visitedPoints)
//        currentlyVisitedPoints.add(currentPoint)
//        if (curX == endingPoint.x && curY == endingPoint.y) return currentlyVisitedPoints.toSet()
//        val possibleSteps = this.possibleSteps(curX, curY, currentlyVisitedPoints.toSet())
//        val pathsToTarget = possibleSteps.map { this.findShortestPathHelper(it.x, it.y, endingPoint, currentlyVisitedPoints.toSet()) }
//        return if (pathsToTarget.isEmpty() || pathsToTarget.all { it.isEmpty() }) {
//            emptySet()
//        } else {
//            pathsToTarget.filter { it.isNotEmpty() }.sortedBy { it.size }[0]
//        }
//    }
//
//    private fun Array<CharArray>.possibleSteps(x: Int, y: Int, visitedPoints: Set<Day12Point>): List<Day12Point> {
//        val currentChar = this[y][x]
//        val possibleSteps = mutableListOf<Day12Point>()
//        for (curX in (x - 1)..(x + 1)) {
//            if (curX == x) continue
//            if (curX < 0 || curX > this[y].lastIndex) continue
//            if (this[y][curX].elevationLevel() - 1 <= currentChar.elevationLevel()) {
//                val possiblePoint = Day12Point(curX, y)
//                if (visitedPoints.none { it == possiblePoint }) possibleSteps.add(possiblePoint)
//            }
//        }
//        for (curY in (y - 1)..(y + 1)) {
//            if (curY == y) continue
//            if (curY < 0 || curY > this.lastIndex) continue
//            if (this[curY][x].elevationLevel() - 1 <= currentChar.elevationLevel()) {
//                val possiblePoint = Day12Point(x, curY)
//                if (visitedPoints.none { it == possiblePoint }) possibleSteps.add(possiblePoint)
//            }
//        }
//        return possibleSteps.toList()
//    }
    ///////////////////////////////////////

    private fun Array<CharArray>.findPoint(point: Char): Day12Point {
        this.forEachIndexed { y, row ->
            row.forEachIndexed { x, height ->
                if (point == height) return Day12Point(x, y)
            }
        }
        return Day12Point(-1, -1)
    }

    private fun Array<CharArray>.findAllPoints(point: Char): List<Day12Point> {
        val points = mutableListOf<Day12Point>()
        this.forEachIndexed { y, row ->
            row.forEachIndexed { x, height ->
                if (point == height) points.add(Day12Point(x, y))
            }
        }
        return points
    }

    private fun Char.elevationLevel(): Int {
        return this.code - 97
    }

    private fun Array<CharArray>.printPath(visitedPoints: Set<Day12Point>) {
        this.forEachIndexed { y, row ->
            row.forEachIndexed { x, c ->
                val point = Day12Point(x, y)
                if (visitedPoints.contains(point)) print("X ") else print(". ")
            }
            println()
        }
        println("==========")
    }

    private fun MutableMap<Day12Point, Set<Day12Point>>.addShortestPathToCurrentPoint(
        currentPoint: Day12Point,
        pathToPoint: Set<Day12Point>
    ) {
        if (this.containsKey(currentPoint)) {
            if (pathToPoint.size < this[currentPoint]!!.size) this[currentPoint] = pathToPoint
        } else {
            this[currentPoint] = pathToPoint
        }

    }

    private fun Array<CharArray>.Dijkstra(
        startingPoint: Day12Point,
        endingPoint: Day12Point,
        switchedElevation: Boolean = false
    ): Map<Day12Point, Int> {
        val dist = generateInitialDistanceMap(startingPoint, this)
        val shortestPathTreeSet = mutableSetOf<Day12Point>()

        while (!shortestPathTreeSet.contains(endingPoint) && dist.filter { !shortestPathTreeSet.contains(it.key) && it.value != Int.MAX_VALUE }
                .isNotEmpty()) {
            val u = dist.getMinimumDistancePoint(shortestPathTreeSet.toSet())
            shortestPathTreeSet.add(u)
            val adjacentPoints = this.adjacentPoints(u, switchedElevation)
            for (point in adjacentPoints) {
                if (dist[u]!! + 1 < dist[point]!!) dist[point] = dist[u]!! + 1
            }
        }
        return dist.toMap()
    }

    private fun generateInitialDistanceMap(
        startingPoint: Day12Point,
        map: Array<CharArray>
    ): MutableMap<Day12Point, Int> {
        return map.flatMapIndexed { y, row ->
            row.mapIndexed { x, c ->
                val initialDistance = if (x == startingPoint.x && y == startingPoint.y) 0 else Int.MAX_VALUE
                Day12Point(x, y) to initialDistance
            }
        }.toMap().toMutableMap()
    }

    private fun Map<Day12Point, Int>.getMinimumDistancePoint(shortestPathTreeSet: Set<Day12Point>): Day12Point {
        return this.filter { !shortestPathTreeSet.contains(it.key) && it.value != Int.MAX_VALUE }.minBy { it.value }.key
    }

    private fun Array<CharArray>.adjacentPoints(
        currentPoint: Day12Point,
        switchedElevation: Boolean = false
    ): List<Day12Point> {
        val currentChar = this[currentPoint.y][currentPoint.x]
        val possibleSteps = mutableListOf<Day12Point>()
        for (curX in (currentPoint.x - 1)..(currentPoint.x + 1)) {
            if (curX == currentPoint.x) continue
            if (curX < 0 || curX > this[currentPoint.y].lastIndex) continue
            if (switchedElevation) {
                if (currentChar.elevationLevel() - this[currentPoint.y][curX].elevationLevel() <= 1) {
                    val possiblePoint = Day12Point(curX, currentPoint.y)
                    possibleSteps.add(possiblePoint)
                }
            } else if (this[currentPoint.y][curX].elevationLevel() - 1 <= currentChar.elevationLevel()) {
                val possiblePoint = Day12Point(curX, currentPoint.y)
                possibleSteps.add(possiblePoint)
            }
        }
        for (curY in (currentPoint.y - 1)..(currentPoint.y + 1)) {
            if (curY == currentPoint.y) continue
            if (curY < 0 || curY > this.lastIndex) continue
            if (switchedElevation) {
                if (currentChar.elevationLevel() - this[curY][currentPoint.x].elevationLevel() <= 1) {
                    val possiblePoint = Day12Point(currentPoint.x, curY)
                    possibleSteps.add(possiblePoint)
                }
            } else if (this[curY][currentPoint.x].elevationLevel() <= currentChar.elevationLevel() - 1) {
                val possiblePoint = Day12Point(currentPoint.x, curY)
                possibleSteps.add(possiblePoint)
            }
        }
        return possibleSteps.toList()
    }

}