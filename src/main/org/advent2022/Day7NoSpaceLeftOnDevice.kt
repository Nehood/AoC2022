package org.advent2022

import org.advent2022.data.Day7File
import org.advent2022.data.Day7Folder

class Day7NoSpaceLeftOnDevice {
    val NO_EXTENSION = "EXTENSION_DOES_NOT_EXIST"
    val TOTAL_DISK_SPACE = 70000000
    val SPACE_REQUIRED_FOR_UPDATE = 30000000

    fun sumDirectoriesOfSizeAtMost(terminalOutput: List<String>, sizeThreshold: Int = 100000): Int {
        val root = parseFilesystem(terminalOutput)
        root.calculateSize()
        return root.sumDirectoriesSizesWithSizeLessThan(sizeThreshold)
    }

    fun findSmallestDirectoryToRemove(terminalOutput: List<String>): Int {
        val root = parseFilesystem(terminalOutput)
        root.calculateSize()
        val currentFreeSize = TOTAL_DISK_SPACE - root.size
        val sizeNeededToFree = SPACE_REQUIRED_FOR_UPDATE - currentFreeSize
        return root.findAllDirectoriesWithSizeGreaterThanThreshold(sizeNeededToFree).minOf { it.size }
    }

    private fun parseFilesystem(terminalOutput: List<String>): Day7Folder {
        var root: Day7Folder? = null
        var currentDir: Day7Folder? = null
        terminalOutput.forEach { line ->
            when {
                line == "$ ls" -> println("Listing directory of ${currentDir?.name}")
                line == "$ cd .." -> currentDir = currentDir?.parentDirectory
                line.startsWith("$ cd") -> {
                    val folderName = line.split(' ').last()
                    if (folderName == "/") {
                        root = Day7Folder(folderName)
                        currentDir = root
                    } else {
                        val dir =
                            currentDir?.directories?.firstOrNull { it.name == folderName } ?: Day7Folder(folderName)
                        if (null == dir.parentDirectory) dir.parentDirectory = currentDir
                        if (currentDir?.directories?.contains(dir) == false) currentDir?.directories?.add(dir)
                        currentDir = dir
                    }
                }

                line.startsWith("dir") -> {
                    val folderName = line.split(' ').last()
                    val dir = currentDir?.directories?.firstOrNull { it.name == folderName } ?: Day7Folder(folderName)
                    if (null == dir.parentDirectory) dir.parentDirectory = currentDir
                    if (currentDir?.directories?.contains(dir) == false) currentDir?.directories?.add(dir)
                }

                line[0].isDigit() -> {
                    val parts = line.split(' ')
                    val size = parts[0].toInt()
                    val fileWithExtension = parts[1].split('.')
                    val filename = fileWithExtension[0]
                    val extension = if (parts[1].contains('.')) {
                        fileWithExtension[1]
                    } else {
                        NO_EXTENSION
                    }
                    currentDir?.files?.add(Day7File(filename, extension, size))
                }

                else -> throw Exception("Unknown command")
            }
        }
        return root!!
    }

    private fun Day7Folder.calculateSize() {
        this.size = this.calculateSizeHelper()
    }

    private fun Day7Folder.calculateSizeHelper(): Int {
        val filesSize = this.files.sumOf { it.size }
        this.size = filesSize
        this.size += this.directories.sumOf {
            if (it.size == 0) it.calculateSizeHelper()
            else it.size
        }
        return this.size
    }

    private fun Day7Folder.sumDirectoriesSizesWithSizeLessThan(sizeThreshold: Int): Int {
        return this.sumDirectoriesSizesWithSizeLessThanHelper(sizeThreshold, 0)
    }

    private fun Day7Folder.sumDirectoriesSizesWithSizeLessThanHelper(sizeThreshold: Int, size: Int): Int {
        var currentSize = size
        if (this.size <= sizeThreshold) currentSize += this.size
        for (dir in this.directories) {
            currentSize = dir.sumDirectoriesSizesWithSizeLessThanHelper(sizeThreshold, currentSize)
        }
        return currentSize
    }

    private fun Day7Folder.findAllDirectoriesWithSizeGreaterThanThreshold(sizeThreshold: Int): List<Day7Folder> {
        val directories = mutableListOf<Day7Folder>()
        this.findAllDirectoriesWithSizeGreaterThanThresholdHelper(sizeThreshold, directories)
        return directories.toList()
    }

    private fun Day7Folder.findAllDirectoriesWithSizeGreaterThanThresholdHelper(
        sizeThreshold: Int, directories: MutableList<Day7Folder>
    ) {
        if (this.size >= sizeThreshold) directories.add(this)
        this.directories.forEach { it.findAllDirectoriesWithSizeGreaterThanThresholdHelper(sizeThreshold, directories) }
    }
}