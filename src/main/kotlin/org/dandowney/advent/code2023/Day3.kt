package org.dandowney.advent.code2023

import org.dandowney.advent.Day

internal class Day3 : Day("2023/day-03.txt") {

  private val pattern = Regex("\\d+")
  private val gearParts = mutableMapOf<String, List<Int>>()

  override fun part1(): String {
    var sum = 0

    for (rowIndex in input.indices) {
      val row = input[rowIndex]
      val numbers = pattern.findAll(row)
      sum += numbers.sumOf { number ->
        for (rowOffset in -1 until 2) {
          if (rowIndex + rowOffset < 0 || rowIndex + rowOffset > input.lastIndex) continue

          for (columnIndex in number.range.first - 1 until number.range.last + 2) {
            if (columnIndex < 0 || columnIndex > row.lastIndex) continue

            if (input[rowIndex + rowOffset][columnIndex].isEngineSymbol()) return@sumOf number.value.toInt()
          }
        }
        0
      }
    }

    return sum.toString()
  }

  override fun part2(): String {
    for (rowIndex in input.indices) {
      val row = input[rowIndex]
      val numbers = pattern.findAll(row)
      numbers.forEach { number ->
        for (rowOffset in -1 until 2) {
          if (rowIndex + rowOffset < 0 || rowIndex + rowOffset > input.lastIndex) continue

          for (columnIndex in number.range.first - 1 until number.range.last + 2) {
            if (columnIndex < 0 || columnIndex > row.lastIndex) continue

            if (input[rowIndex + rowOffset][columnIndex].isGearSymbol()) {
              val key = "${rowIndex + rowOffset}-$columnIndex"
              gearParts[key] = gearParts[key]?.plus(number.value.toInt()) ?: listOf(number.value.toInt())
              return@forEach
            }
          }
        }
      }
    }
//    println(gearParts)
    return gearParts
      .values
      .filter { it.size == 2 }
      .sumOf { it[0] * it[1] }
      .toString()
  }

  private fun Char.isEngineSymbol(): Boolean = !isDigit() && this != '.'

  private fun Char.isGearSymbol(): Boolean = this == '*'
}