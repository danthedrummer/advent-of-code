package org.dandowney.advent.code2023

import org.dandowney.advent.Day

internal class Day2 : Day("2023/day-02.txt") {
  override fun part1(): String {
    val constraint = CubeSet(r = 12, g = 13, b = 14)

    val answer = input
      .mapNotNull { line ->
        val (id, sets) = line.split(":")
        sets.split(";").forEach { set ->
          set.split(",").forEach { result ->
            val data = result.filter { it.isDigit() }.toInt()
            when {
              result.contains("red") -> if (data > constraint.r) return@mapNotNull null
              result.contains("green") -> if (data > constraint.g) return@mapNotNull null
              result.contains("blue") -> if (data > constraint.b) return@mapNotNull null
            }
          }
        }
        id.filter { it.isDigit() }.toInt()
      }
      .sum()

    return answer.toString()
  }

  override fun part2(): String {

    val answer = input.sumOf { line ->
      var constraint = CubeSet(r = 0, g = 0, b = 0)
      val (_, sets) = line.split(":")
      sets.split(";").forEach { set ->
        set.split(",").forEach { result ->
          val data = result.filter { it.isDigit() }.toInt()
          when {
            result.contains("red") -> if (data > constraint.r) constraint = constraint.copy(r = data)
            result.contains("green") -> if (data > constraint.g) constraint = constraint.copy(g = data)
            result.contains("blue") -> if (data > constraint.b) constraint = constraint.copy(b = data)
          }
        }
      }
      constraint.r * constraint.g * constraint.b
    }

    return answer.toString()
  }

  data class CubeSet(
    val r: Int = 0,
    val g: Int = 0,
    val b: Int = 0,
  )
}
