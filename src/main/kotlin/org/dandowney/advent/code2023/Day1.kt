package org.dandowney.advent.code2023

import org.dandowney.advent.Day

internal class Day1 : Day("2023/day-01.txt") {
  override fun part1(): String {
    return input
      .map { line -> line.first { it.isDigit() }.toString() + line.last { it.isDigit() }.toString() }
      .sumOf { it.toInt() }
      .toString()
  }

  override fun part2(): String {
    val spelledNumbers = setOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    return input.sumOf { line ->
      var first: Int? = null
      var last: Int = -1
      for (lineIndex in line.indices) {
        if (line[lineIndex].isDigit()) {
          last = line[lineIndex].digitToInt()
          first = first ?: last
        }
        spelledNumbers.forEachIndexed { numIndex, spelledNumber ->
          if (lineIndex + spelledNumber.length > line.length) {
            // do nothing
          } else if (spelledNumber == line.subSequence(lineIndex, lineIndex + spelledNumber.length)) {
            last = numIndex + 1
            first = first ?: last
          }
        }
      }
      "$first$last".toInt()
    }.toString()
  }
}