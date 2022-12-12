package org.dandowney.advent.code2021

import org.dandowney.advent.Day

internal class Day1 : Day("2021-12-01.txt") {

  override fun part1(): String {
    var count = 0
    var previous = input.first().toInt()

    for (i in 1 until input.size) {
      val current = input[i].toInt()
      if (current > previous) count++
      previous = current
    }

    return count.toString()
  }

  override fun part2(): String {
    var count = 0
    val digits = mutableListOf<Int>()
    var previousSum = 0

    for (i in input.indices) {
      val number = input[i].toInt()
      previousSum = if (digits.size < 3) {
        digits.add(number)
        digits.sum()
      } else {
        digits.removeAt(0)
        digits.add(number)
        digits.sum().also { if (it > previousSum) count++ }
      }
    }

    return count.toString()
  }
}