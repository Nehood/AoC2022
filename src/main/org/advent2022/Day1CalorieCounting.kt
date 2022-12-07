package org.advent2022

class Day1CalorieCounting {
    fun countCaloriesOfNOfFattestElves(calories: List<List<Int>>, n: Int = 1): Int {
        val caloriesSummed = calories.map { it.sum() }.sortedDescending()
        return caloriesSummed.take(n).sum()
    }
}