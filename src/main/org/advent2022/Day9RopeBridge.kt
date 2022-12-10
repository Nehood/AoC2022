package org.advent2022

import org.advent2022.data.Day9Point
import kotlin.math.abs

class Day9RopeBridge {
    fun countNKnotsVisitedPoints(motions: List<String>, n: Int = 9): Int {
        val headPosition = Day9Point(0, 0)
        val tailPositions = (1..n).map { headPosition.copy() }

        val tailVisitedPositions = mutableSetOf(headPosition.copy())

        for (motion in motions) {
            val direction = motion.split(' ')[0]
            val stepsCount = motion.split(' ')[1].toInt()

            when (direction) {
                "R" -> {
                    for (step in 1..stepsCount) {
                        headPosition.x += 1
                        tailPositions[0].moveTail(headPosition)
                        for (knotIndex in 1..tailPositions.lastIndex) {
                            tailPositions[knotIndex].moveTail(tailPositions[knotIndex - 1])
                        }
                        tailVisitedPositions.add(tailPositions.last().copy())
                    }
                }

                "L" -> {
                    for (step in 1..stepsCount) {
                        headPosition.x -= 1
                        tailPositions[0].moveTail(headPosition)
                        for (knotIndex in 1..tailPositions.lastIndex) {
                            tailPositions[knotIndex].moveTail(tailPositions[knotIndex - 1])
                        }
                        tailVisitedPositions.add(tailPositions.last().copy())
                    }
                }

                "U" -> {
                    for (step in 1..stepsCount) {
                        headPosition.y += 1
                        tailPositions[0].moveTail(headPosition)
                        for (knotIndex in 1..tailPositions.lastIndex) {
                            tailPositions[knotIndex].moveTail(tailPositions[knotIndex - 1])
                        }
                        tailVisitedPositions.add(tailPositions.last().copy())
                    }
                }

                "D" -> {
                    for (step in 1..stepsCount) {
                        headPosition.y -= 1
                        tailPositions[0].moveTail(headPosition)
                        for (knotIndex in 1..tailPositions.lastIndex) {
                            tailPositions[knotIndex].moveTail(tailPositions[knotIndex - 1])
                        }
                        tailVisitedPositions.add(tailPositions.last().copy())
                    }
                }

                else -> throw Exception("Unknown command!")
            }
        }
        return tailVisitedPositions.size
    }

    fun countTailVisitedPoints(motions: List<String>): Int {
        val headPosition = Day9Point(0, 0)
        val tailPosition = headPosition.copy()

        val tailVisitedPositions = mutableSetOf(tailPosition.copy())

        for (motion in motions) {
            val direction = motion.split(' ')[0]
            val stepsCount = motion.split(' ')[1].toInt()

            when (direction) {
                "R" -> {
                    for (step in 1..stepsCount) {
                        headPosition.x += 1
                        tailPosition.moveTail(headPosition)
                        tailVisitedPositions.add(tailPosition.copy())
                    }
                }

                "L" -> {
                    for (step in 1..stepsCount) {
                        headPosition.x -= 1
                        tailPosition.moveTail(headPosition)
                        tailVisitedPositions.add(tailPosition.copy())
                    }
                }

                "U" -> {
                    for (step in 1..stepsCount) {
                        headPosition.y += 1
                        tailPosition.moveTail(headPosition)
                        tailVisitedPositions.add(tailPosition.copy())
                    }
                }

                "D" -> {
                    for (step in 1..stepsCount) {
                        headPosition.y -= 1
                        tailPosition.moveTail(headPosition)
                        tailVisitedPositions.add(tailPosition.copy())
                    }
                }

                else -> throw Exception("Unknown command!")
            }
        }
        return tailVisitedPositions.size
    }

    private fun Day9Point.moveTail(
        headPosition: Day9Point
    ) {
        if (abs(headPosition.x - this.x) <= 1 && abs(headPosition.y - this.y) <= 1) return
        if (headPosition.y != this.y) this.y += if (headPosition.y > this.y) 1 else -1
        if (headPosition.x != this.x) this.x += if (headPosition.x > this.x) 1 else -1
    }


}