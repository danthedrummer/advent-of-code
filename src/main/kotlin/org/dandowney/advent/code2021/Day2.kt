package org.dandowney.advent.code2021

import org.dandowney.advent.Day

internal class Day2 : Day("2021/day-02.txt") {

  override fun part1(): String {
    var distance = 0
    var depth = 0

    input.map { command ->
      command.split(" ").let { it.first() to it.last().toInt() }
    }.forEach { (instruction, magnitude) ->
      when (instruction) {
        "forward" -> distance += magnitude
        "up" -> depth -= magnitude
        "down" -> depth += magnitude
      }
    }

    return "Submarine movement = ${distance * depth}"
  }

  override fun part2(): String {
    var distance = 0
    var depth = 0
    var aim = 0


    input.map { command ->
      command.split(" ").let { it.first() to it.last().toInt() }
    }.forEach { (instruction, magnitude) ->
      when (instruction) {
        "forward" -> {
          distance += magnitude
          depth += aim * magnitude
        }
        "up" -> aim -= magnitude
        "down" -> aim += magnitude
      }
    }

    return "Submarine movement with aim = ${distance * depth}"
  }
}