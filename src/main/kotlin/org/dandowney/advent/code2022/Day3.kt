package org.dandowney.advent.code2022

import org.dandowney.advent.Day

internal class Day3 : Day("2022-12-03.txt") {

  override fun part1(): String {
    var result = 0
    input
      .map { contents ->
        contents.substring(0, contents.length / 2) to contents.substring(contents.length / 2)
      }
      .forEach { compartments ->
        result += compartments.first
          .find { item -> item in compartments.second }
          ?.let { item -> (item.code) - if (item.isUpperCase()) 38 else 96 }
          ?: 0
      }
    return result.toString()
  }

  override fun part2(): String {
    val groups = mutableListOf<Group>()
    var group = Group()
    input.forEach { backpack ->
      group.backpacks.add(backpack)
      if (group.backpacks.size == 3) {
        groups.add(group)
        group = Group()
      }
    }

    var result = 0
    groups.forEach { group ->
      val badge = group.backpacks.first()
        .find { it in group.backpacks[1] && it in group.backpacks[2] }
        ?: error("Should have found a badge in ${group.backpacks}")
      result += (badge.code) - if (badge.isUpperCase()) 38 else 96
    }
    return result.toString()
  }
}

internal data class Group(
  val backpacks: MutableList<String> = mutableListOf(),
)