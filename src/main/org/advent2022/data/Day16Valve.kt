package org.advent2022.data

data class Day16Valve(val label: String, val flowRate: Int, var stateOpened: Boolean = false, val connectedValves: MutableList<String> = mutableListOf()) {
    fun clone(label: String = this.label, flowRate: Int = this.flowRate, stateOpened: Boolean = this.stateOpened, connectedValves: MutableList<String> = this.connectedValves) = Day16Valve(label, flowRate, stateOpened, connectedValves)
}