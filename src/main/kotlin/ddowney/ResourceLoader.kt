package ddowney

object ResourceLoader {

  fun load(filename: String): List<String> = this::class.java.classLoader
    .getResource(filename)
    ?.openStream()
    ?.bufferedReader()
    ?.readLines()
    ?: error("File not found")
}