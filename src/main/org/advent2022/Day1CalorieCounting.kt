package org.advent2022

class Day1CalorieCounting {
    fun countCaloriesOfFattestElf(calories: List<List<Int>>): Int {
        val caloriesSummed = calories.map { it.sum() }
        return caloriesSummed.max()
    }

    fun countCaloriesOfNOfFattestElves(calories: List<List<Int>>, n: Int = 3): Int {
        val caloriesSummed = calories.map { it.sum() }.sortedDescending()
        return caloriesSummed.take(n).sum()
    }
}