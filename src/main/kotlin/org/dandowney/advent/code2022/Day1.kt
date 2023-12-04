package org.dandowney.advent.code2022

import org.dandowney.advent.Day

internal class Day1 : Day("2022/day-01.txt") {

  override fun part1(): String = processElves()
    .maxOfOrNull { it.totalCalories() }
    ?.toString()
    ?: "Found nothing"

  override fun part2(): String {
    val elves = processElves()
    return elves.map { it.totalCalories() }
      .sortedByDescending { it }
      .take(3)
      .sum()
      .toString()
  }

  private fun processElves(): List<Elf> {
    val elves = mutableListOf<Elf>()
    var currentElf = Elf()

    input.forEach { entry ->
      if (entry.isBlank()) {
        elves.add(currentElf)
        currentElf = Elf()
      } else {
        currentElf.items.add(entry.toInt())
      }
    }

    return elves
  }
}

data class Elf(
  val items: MutableList<Int> = mutableListOf(),
) {

  fun totalCalories(): Int = items.sum()
}