package org.dandowney.advent.code2022

import org.dandowney.advent.Day
import org.dandowney.advent.code2022.Choice.Companion.fromPrediction

internal class Day2 : Day("2022/day-02.txt") {

  override fun part1(): String {
    var score = 0
    input.forEach { round ->
      val (opponent, player) = round.split(" ").map(Choice::fromInput)
      score += player.score + player.result(opponent)
    }
    return score.toString()
  }

  override fun part2(): String {
    var score = 0
    input.forEach { round ->
      val temp = round.split(" ")
      val opponent = Choice.fromInput(temp[0])
      val player = try {
        opponent.fromPrediction(temp[1])
      } catch (t: Throwable) {
        println(temp)
        throw t
      }
      score += player.score + player.result(opponent)
    }
    return score.toString()
  }
}

internal enum class Choice(val score: Int) {
  ROCK(1),
  PAPER(2),
  SCISSORS(3),
  ;

  fun result(opponent: Choice): Int = if (ordinal == opponent.ordinal) {
    3
  } else if ((ordinal + 1) % 3 == opponent.ordinal) {
    0
  } else {
    6
  }

  companion object {

    fun fromInput(input: String): Choice = when (input) {
      "A", "X" -> ROCK
      "B", "Y" -> PAPER
      "C", "Z" -> SCISSORS
      else -> error("input [$input] not allowed")
    }

    fun Choice.fromPrediction(input: String): Choice = when (input) {
      "X" -> Choice.values()[(ordinal - 1 + 3) % 3]
      "Y" -> Choice.values()[ordinal]
      "Z" -> Choice.values()[(ordinal + 1) % 3]
      else -> error("input [$input] not allowed")
    }

    // X means you need to lose
    // Y means you need to end the round in a draw
    // Z means you need to win
  }
}
