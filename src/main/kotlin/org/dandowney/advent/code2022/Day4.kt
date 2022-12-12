package org.dandowney.advent.code2022

import org.dandowney.advent.Day

internal class Day4 : Day("2022-12-04.txt") {

  override fun part1(): String {
    var containedPairs = 0
    input.forEach { assignments ->
      val (startA, endA, startB, endB) = assignments.split("-", ",").map { it.toInt() }
      val a = asFlagSet(startA..endA)
      val b = asFlagSet(startB..endB)
      var isAContained = true
      var isBContained = true

      repeat(5) { i ->
        val mask = a[i] and b[i]
        isAContained = isAContained && (mask == a[i])
        isBContained = isBContained && (mask == b[i])
      }
      if (isAContained || isBContained) containedPairs++
    }

    return containedPairs.toString()
  }

  override fun part2(): String {
    var containedPairs = 0
    input.forEach { assignments ->
      val (startA, endA, startB, endB) = assignments.split("-", ",").map { it.toInt() }
      val a = asFlagSet(startA..endA)
      val b = asFlagSet(startB..endB)

      var hasOverlap = false
      repeat(5) loop@ { i ->
        if (a[i] and b[i] != 0) {
          hasOverlap = true
          return@loop
        }
      }
      if (hasOverlap) containedPairs++
    }

    return containedPairs.toString()
  }

  private fun asFlagSet(range: IntRange): List<Int> {
    val flags = mutableListOf<Int>()
    repeat(5) { iteration ->
      flags.add(
        ByteArray(20) { index ->
          if (index + (iteration * 20) in range) 1 else 0
        }.joinToString("").toInt(2)
      )
    }
    return flags
  }
}