package ddowney

import java.util.*

fun main() {
  val keyboard = Scanner(System.`in`)

  while (true) {
    println("What day is it? (q to quit) -> ")
    val input = keyboard.nextLine()
    if (input == "q") break
    val day = Class.forName("ddowney.days.Day${input}")
      .getDeclaredConstructor()
      .newInstance() as Day
    println(day.part1())
    println(day.part2())
  }
}