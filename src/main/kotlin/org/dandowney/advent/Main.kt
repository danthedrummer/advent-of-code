package org.dandowney.advent

import java.util.*

fun main() {
  val keyboard = Scanner(System.`in`)
  val year = 2023

  while (true) {
    println("What day is it? (q to quit) -> ")
    val input = keyboard.nextLine()
    if (input == "q") break
    val day = Class.forName("org.dandowney.advent.code${year}.Day${input}")
      .getDeclaredConstructor()
      .newInstance() as Day
    println(day.part1())
    println(day.part2())
  }
}