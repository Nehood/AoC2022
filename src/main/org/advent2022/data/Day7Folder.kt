package org.advent2022.data

class Day7Folder(val name: String) {
    var parentDirectory: Day7Folder? = null
    val directories = mutableSetOf<Day7Folder>()
    val files = mutableSetOf<Day7File>()
    var size = 0

    @Override
    override fun toString(): String {
        return name
    }
}