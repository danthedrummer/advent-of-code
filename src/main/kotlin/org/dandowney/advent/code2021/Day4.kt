package org.dandowney.advent.code2021

import org.dandowney.advent.Day

internal class Day4 : Day("2021/day-04.txt") {
  override fun part1(): String {
    val numbers = input.first().split(",").map { it.toInt() }
    val boards = parseBoards()

    numbers.forEach { number ->
      boards.forEach { board ->
        if (board.mark(number)) {
          return "Bingo total = ${board.total * number}"
        }
      }
    }
    return "No winner :("
  }

  override fun part2(): String {
    val numbers = input.first().split(",").map { it.toInt() }
    val boards = parseBoards()

    numbers.forEach { number ->
      val winners = mutableListOf<BingoBoard>()
      boards.forEach { board ->
        if (board.mark(number)) {
          if (boards.size == 1) {
            return "Last winner total = ${board.total * number}"
          }
          winners.add(board)
        }
      }
      boards.removeAll(winners)
    }
    return "No winner :("
  }

  private fun parseBoards(): MutableList<BingoBoard> {
    val boards = mutableListOf<BingoBoard>()
    val digits = mutableListOf<Int>()
    input.subList(2, input.size).forEach { line ->
      if (line.isNotEmpty()) {
        digits.addAll(line.split(" ").filter { it.isNotEmpty() }.map { it.toInt() })
      }
      if (digits.size == 25) {
        boards.add(BingoBoard(digits.toMutableList()))
        digits.clear()
      }
    }
    return boards
  }

}

data class BingoBoard(
  private val numbers: MutableList<Int>,
) {

  var total = numbers.sum()

  fun mark(number: Int): Boolean {
    println("Calling number $number")
    if (numbers.contains(number)) {
      numbers[numbers.indexOf(number)] = -1
      total -= number
    }
    return checkForBingo()
  }

  private fun checkForBingo(): Boolean {
    println(toString())
    for (i in 0 until 5) {
      var bingo = true
      for (j in 0 until 5) {
        if (numbers[(i * 5) + j] != -1) {
          bingo = false
        }
      }
      if (bingo) {
        return true
      }
    }
    for (i in 0 until 5) {
      var bingo = true
      for (j in 0 until 5) {
        if (numbers[i + (j * 5)] != -1) {
          bingo = false
        }
      }
      if (bingo) {
        return true
      }
    }
    return false
  }
}