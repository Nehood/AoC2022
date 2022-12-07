package org.advent2022.data

class Day7File(val filename: String, val extension: String, val size: Int) {
    override fun toString(): String {
        return "$filename.$extension"
    }
}