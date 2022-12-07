package org.advent2022

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.io.File

internal class Day6TuningTroubleTest {

    private val day6TuningTrouble = Day6TuningTrouble()
    private val inputFileName = "Day6Input.txt"

    private fun readResourceFile(): String {
        val resourcesPath = "src/test/resources"
        return File("$resourcesPath/$inputFileName").readText()
    }

    @ParameterizedTest
    @CsvSource(
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb,7",
        "bvwbjplbgvbhsrlpgdmjqwftvncz,5",
        "nppdvjthqldpwncqszvftbrmjlhg,6",
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg,10",
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,11"
    )
    fun findFirstStartOfPacketMarkerTest(buffer: String, expectedLocation: String) {
        val firstStartOfPacketMarker = day6TuningTrouble.findFirstStartOfPacketMarker(buffer)

        assertEquals(expectedLocation.toInt(), firstStartOfPacketMarker)
    }

    @Test
    fun findFirstStartOfPacketMarker() {
        val firstStartOfPacketMarker = day6TuningTrouble.findFirstStartOfPacketMarker(readResourceFile())
        val expected = 1647
        assertEquals(expected, firstStartOfPacketMarker)
    }

    @ParameterizedTest
    @CsvSource(
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb,19",
        "bvwbjplbgvbhsrlpgdmjqwftvncz,23",
        "nppdvjthqldpwncqszvftbrmjlhg,23",
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg,29",
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,26"
    )
    fun findFirstStartOfPacketMarkerMessageTest(buffer: String, expectedLocation: String) {
        val firstStartOfPacketMarker = day6TuningTrouble.findFirstStartOfPacketMarker(buffer, 14)

        assertEquals(expectedLocation.toInt(), firstStartOfPacketMarker)
    }

    @Test
    fun findFirstStartOfPacketMarkerMessage() {
        val firstStartOfPacketMarker = day6TuningTrouble.findFirstStartOfPacketMarker(readResourceFile(), 14)
        val expected = 2447
        assertEquals(expected, firstStartOfPacketMarker)
    }
}