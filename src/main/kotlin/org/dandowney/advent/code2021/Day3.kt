package org.dandowney.advent.code2021

import org.dandowney.advent.Day
import kotlin.math.pow

internal class Day3 : Day("2021-12-03.txt") {

  override fun part1(): String {
    var gamma = 0.0
    var epsilon = 0.0
    val width = input.first().length
    for (i in width - 1 downTo 0) {
      var diff = 0
      input.forEach { if (it[i] == '1') diff++ else diff-- }
      val value = 2.0.pow(width - 1 - i)
      if (diff > 0) gamma += value else epsilon += value
    }
    return "Diagnostic report = ${gamma * epsilon}"
  }

  override fun part2(): String {
    val oxygen = calculateValue(input) { a, b -> if (a.size >= b.size) a else b }
    val cO2 = calculateValue(input) { a, b -> if (a.size < b.size) a else b }

    return "Life support rating = ${oxygen * cO2}"
  }

  private fun calculateValue(
    original: List<String>,
    selector: (List<String>, List<String>) -> List<String>,
  ): Int {
    var result = original
    for (i in 0 until original.first().length) {
      val a = mutableListOf<String>()
      val b = mutableListOf<String>()
      result.forEach { if (it[i] == '1') a.add(it) else b.add(it) }
      result = selector(a, b)
      if (result.size == 1) break
    }
    return result.first().toInt(2)
  }

}