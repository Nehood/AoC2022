package org.advent2022

class Day8TreetopTreeHouse {
    fun countTreesVisibleFromOutside(trees: Array<Array<Int>>): Int {
        return trees.countVisibleTrees()
    }

    fun findHighestScenicScoreForTreeHouse(trees: Array<Array<Int>>): Int {
        return trees.findHighestScenicScore()
    }

    private fun Array<Array<Int>>.findHighestScenicScore(): Int {
        return this.flatMapIndexed { y, row ->
            row.mapIndexed { x, _ ->
                row.calculateScenicDistances(x) * this.getColumn(x).calculateScenicDistances(y)
            }
        }.max()
    }

    private fun Array<Array<Int>>.countVisibleTrees(): Int {
        return this.flatMapIndexed { y, row ->
            row.mapIndexed { x, _ ->
                if (x == 0 || y == 0 || y == this.lastIndex || x == this[0].lastIndex) true
                row.countVisibilityFromBothSides(x) || this.getColumn(x).countVisibilityFromBothSides(y)
            }
        }.count { it }
    }

    private fun Array<Array<Int>>.getColumn(x: Int): Array<Int> {
        return this.map { it[x] }.toTypedArray()
    }

    private fun Array<Int>.countVisibilityFromBothSides(index: Int): Boolean {
        val height = this[index]
        return this.copyOfRange(0, index).all { it < height } || this.copyOfRange(index + 1, this.size)
            .all { it < height }
    }

    private fun Array<Int>.calculateScenicDistances(index: Int): Int {
        val xd = 1
        return this.copyOfRange(0, index).reversedArray()
            .calculateViewingDistance(this[index]) * this.copyOfRange(index + 1, this.size)
            .calculateViewingDistance(this[index])
    }

    private fun Array<Int>.calculateViewingDistance(height: Int): Int {
        val visibleTrees = this.takeWhile { it < height }.count()
        return if (visibleTrees < this.size) visibleTrees + 1 else visibleTrees //add blocking tree if view is obstructed
    }
}