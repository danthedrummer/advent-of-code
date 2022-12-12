package org.dandowney.advent

internal abstract class Day(filename: String) {

  protected val input: List<String> = ResourceLoader.load(filename)

  abstract fun part1(): String

  abstract fun part2(): String
}