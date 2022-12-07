package org.advent2022

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class Day7NoSpaceLeftOnDeviceTest {

    private val day7NoSpaceLeftOnDevice = Day7NoSpaceLeftOnDevice()
    private val inputFileName = "Day7Input.txt"

    private fun readResourceFile(): List<String> {
        val resourcesPath = "src/test/resources"
        return File("$resourcesPath/$inputFileName").readLines()
    }

    @Test
    fun sumDirectoriesOfSizeAtMost100000Test() {
        val input = """
            $ cd /
            $ ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
            $ cd a
            $ ls
            dir e
            29116 f
            2557 g
            62596 h.lst
            $ cd e
            $ ls
            584 i
            $ cd ..
            $ cd ..
            $ cd d
            $ ls
            4060174 j
            8033020 d.log
            5626152 d.ext
            7214296 k
        """.trimIndent().lines()
        val sumOfDirectoriesSize = day7NoSpaceLeftOnDevice.sumDirectoriesOfSizeAtMost(input)
        val expected = 95437

        Assertions.assertEquals(expected, sumOfDirectoriesSize)
    }

    @Test
    fun sumDirectoriesOfSizeAtMost100000() {
        val sumOfDirectoriesSize = day7NoSpaceLeftOnDevice.sumDirectoriesOfSizeAtMost(readResourceFile())
        val expected = 1390824

        Assertions.assertEquals(expected, sumOfDirectoriesSize)
    }

    @Test
    fun findSmallestDirectoryToRemoveTest() {
        val input = """
            $ cd /
            $ ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
            $ cd a
            $ ls
            dir e
            29116 f
            2557 g
            62596 h.lst
            $ cd e
            $ ls
            584 i
            $ cd ..
            $ cd ..
            $ cd d
            $ ls
            4060174 j
            8033020 d.log
            5626152 d.ext
            7214296 k
        """.trimIndent().lines()
        val sumOfDirectoriesSize = day7NoSpaceLeftOnDevice.findSmallestDirectoryToRemove(input)
        val expected = 24933642

        Assertions.assertEquals(expected, sumOfDirectoriesSize)
    }

    @Test
    fun findSmallestDirectoryToRemove() {
        val sumOfDirectoriesSize = day7NoSpaceLeftOnDevice.findSmallestDirectoryToRemove(readResourceFile())
        val expected = 7490863

        Assertions.assertEquals(expected, sumOfDirectoriesSize)
    }

}