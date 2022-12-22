package org.advent2022

import org.advent2022.data.Day16Valve

class Day16ProboscideaVolcanium {
    fun releaseMostPressure(scan: List<String>): Int {
        val valves = scan.readScan()
        val startingValve = "AA"
        val allPaths = valves.associate { it.label to Dijkstra(valves.associateBy { valve -> valve.label }, it.label) }
        return releaseMostPressure(allPaths, startingValve)
    }

    private fun List<String>.readScan(): List<Day16Valve> {
        val parts = this.map { it.split("; ") }
        val valves = parts.map { it[0].split(" has flow rate=") }
            .map { Day16Valve(it[0].substringAfter("Valve "), it[1].toInt()) }
        val valveTunnels = parts.map {
            it[1].replace("tunnels lead to valves ", "").replace("tunnel leads to valve ", "")
        }.map { it.split(", ") }
        valves.forEachIndexed { index, valve ->
            valve.connectedValves.addAll(valveTunnels[index])
        }
        return valves
    }

    private fun releaseMostPressure(allPaths: Map<String, Map<Day16Valve, Int>>, startingValveLabel: String): Int {
        val time = 30
        return releaseMostPressureHelper(allPaths, startingValveLabel, time, emptySet(), 0, 0)
    }

    private fun releaseMostPressureHelper(
        allPaths: Map<String, Map<Day16Valve, Int>>,
        currentValveLabel: String,
        remainingTime: Int,
        openedValves: Set<String>,
        currentFlowRate: Int,
        currentlyReleasedPressure: Int
    ): Int {
        //println("time passed: ${30 - remainingTime}, openedValves: $openedValves, currentFlowRate: $currentFlowRate, currentlyReleasedPressure: $currentlyReleasedPressure")
        val currentPaths = allPaths[currentValveLabel]!!
        val possibleMeaningfulPaths =
            currentPaths.filter { it.key.flowRate != 0 }.filter { !openedValves.contains(it.key.label) }
        if (possibleMeaningfulPaths.isEmpty()) {
            return currentlyReleasedPressure + (currentFlowRate * remainingTime)
        }
        return possibleMeaningfulPaths.map {
            if (it.value < remainingTime) {
                val currentlyOpenedValves = openedValves.toMutableSet()
                currentlyOpenedValves.add(it.key.label)
                val newFlowRate = currentFlowRate + it.key.flowRate
                val newReleasedPressure = currentlyReleasedPressure + (currentFlowRate * (it.value + 1))
                releaseMostPressureHelper(
                    allPaths,
                    it.key.label,
                    remainingTime - (it.value + 1),
                    currentlyOpenedValves.toSet(),
                    newFlowRate,
                    newReleasedPressure
                )
            } else {
                currentlyReleasedPressure + currentFlowRate * remainingTime
            }
        }.max()
    }

    private fun Dijkstra(valves: Map<String, Day16Valve>, startingValve: String): Map<Day16Valve, Int> {
        val dist = generateInitialDistanceMap(startingValve, valves.values)
        val shortestPathTreeSet = mutableSetOf<Day16Valve>()

        while (shortestPathTreeSet.size != valves.size) {
            val u = dist.getMinimumDistancePoint(shortestPathTreeSet.toSet())
            shortestPathTreeSet.add(u)
            val adjacentValves = valves[u.label]!!.connectedValves
            for (valve in adjacentValves) {
                if (dist[u]!! + 1 < dist[valves[valve]]!!) dist[valves[valve]!!] = dist[u]!! + 1
            }
        }
        return dist.toMap()
    }


    private fun generateInitialDistanceMap(
        startingPoint: String, valves: Collection<Day16Valve>
    ): MutableMap<Day16Valve, Int> {
        return valves.associateWith {
            if (it.label == startingPoint) 0 else Int.MAX_VALUE
        }.toMutableMap()
    }

    private fun Map<Day16Valve, Int>.getMinimumDistancePoint(shortestPathTreeSet: Set<Day16Valve>): Day16Valve {
        return this.filter { !shortestPathTreeSet.contains(it.key) && it.value != Int.MAX_VALUE }.minBy { it.value }.key
    }
}