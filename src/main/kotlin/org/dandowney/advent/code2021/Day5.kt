package org.dandowney.advent.code2021

import org.dandowney.advent.Day
import java.lang.Integer.min
import kotlin.math.abs
import kotlin.math.max

internal class Day5 : Day("2021-12-05.txt") {

  override fun part1(): String {
    val grid: MutableList<MutableList<Int>> = MutableList(1000) {
      MutableList(1000) { 0 }
    }
    val lines = parseData()

    lines.forEach { line ->
      if (line.start.y == line.end.y) {
        val begin = min(line.start.x, line.end.x)
        val end = max(line.start.x, line.end.x)
        for (i in begin..end) {
          grid[line.start.y][i] += 1
        }
      } else if (line.start.x == line.end.x) {
        val begin = min(line.start.y, line.end.y)
        val end = max(line.start.y, line.end.y)
        for (i in begin..end) {
          grid[i][line.start.x] += 1
        }
      } else {
        // Ignoring diagonals
      }
    }

    return "Number of straight line overlaps = ${grid.flatten().count { it >= 2 }}"
  }

  override fun part2(): String {
    val gridSize = 1000
    val grid: MutableList<MutableList<Int>> = MutableList(gridSize) {
      MutableList(gridSize) { 0 }
    }
    val lines = parseData()

    lines.forEach { line ->
      val xDiff = line.end.x - line.start.x
      val yDiff = line.end.y - line.start.y
      val xTransform = calculateTransform(xDiff)
      val yTransform = calculateTransform(yDiff)
      val steps = max(abs(xDiff), abs(yDiff))
      var i = line.start.x
      var j = line.start.y
      grid[j][i] += 1
      repeat(steps) {
        grid[j + yTransform][i + xTransform] += 1
        i += xTransform
        j += yTransform
      }
    }

    if (gridSize <= 10) {
      // For debugging against a smaller data set
      displayGrid(grid)
    }

    return "Number of overlaps = ${grid.flatten().count { it >= 2 }}"
  }

  private fun parseData(): List<Line> = input.map { entry ->
    entry.split(" -> ").map { lines ->
      val (x, y) = lines.split(",").map { it.toInt() }
      Point(x, y)
    }.let { points ->
      val (start, end) = points
      Line(start, end)
    }
  }

  private fun calculateTransform(diff: Int): Int = if (diff > 0) {
    1
  } else if (diff < 0) {
    -1
  } else {
    0
  }

  private fun displayGrid(grid: List<List<Int>>) {
    grid.forEach { row ->
      row.forEach { count -> print("$count, ") }
      println()
    }
  }

}

data class Point(val x: Int, val y: Int)

data class Line(val start: Point, val end: Point)