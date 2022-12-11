package org.advent2022.data

import java.math.BigInteger

data class Day11Monkey(val id: Int, val operationSign: Char, val operationValue: Int, val testValue: Int, val trueTarget: Int, val falseTarget: Int) {
    val items = mutableListOf<BigInteger>()
    var inspectionsCount = BigInteger.ZERO

    companion object {

        private const val STARTING_ITEMS = "Starting items: "
        private const val OPERATION = "Operation: new = old "
        private const val DIVISIBLE = "Test: divisible by "
        private const val THROW = "throw to monkey "

        fun fromNote(note: List<String>): Day11Monkey {
            val id = note[0].split(' ')[1].replace(":", "").toInt()
            val startingItems = note[1].substringAfter(STARTING_ITEMS).replace(",", "").split(' ').map { it.toInt() }
            val operationParts = note[2].substringAfter(OPERATION).split(' ')
            val operationSign = operationParts[0][0]
            val operationValue = if (operationParts[1] == "old") -1 else operationParts[1].toInt()
            val testValue = note[3].substringAfter(DIVISIBLE).toInt()
            val trueTarget = note[4].substringAfter(THROW).toInt()
            val falseTarget = note[5].substringAfter(THROW).toInt()

            val monkey = Day11Monkey(id, operationSign, operationValue, testValue, trueTarget, falseTarget)
            monkey.items.addAll(startingItems.map { it.toBigInteger() })
            return monkey
        }
    }

    fun play(monkeys: Array<Day11Monkey>, masterDivisor: Int, relief: Int) {
        for (worryLevel in items) {
            ++inspectionsCount
            val newWorryLevel = executeOperation(worryLevel)
            val relievedWorryLevel = newWorryLevel / relief.toBigInteger()
            if (test(relievedWorryLevel)) monkeys[trueTarget].items.add(relievedWorryLevel.mod(masterDivisor.toBigInteger())) else monkeys[falseTarget].items.add(relievedWorryLevel.mod(masterDivisor.toBigInteger()))
        }
        items.clear()
    }

    private fun executeOperation(worryLevel: BigInteger): BigInteger {
        val trueOperationValue = if (operationValue == -1) worryLevel else operationValue.toBigInteger()
        return when (operationSign) {
            '*' -> worryLevel.times(trueOperationValue)
            '/' -> worryLevel.divide(trueOperationValue)
            '+' -> worryLevel.plus(trueOperationValue)
            '-' -> worryLevel.minus(trueOperationValue)
            else -> throw Exception("Unknown operation")
        }
    }

    private fun test(worryLevel: BigInteger): Boolean {
        return worryLevel.mod(testValue.toBigInteger()) == BigInteger.ZERO
    }
}