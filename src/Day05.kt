import kotlin.math.absoluteValue

data class Vent(
    val x1: Int,
    val y1: Int,
    val x2: Int,
    val y2: Int
)

fun main() {

    fun process(input: List<String>, considerDiagonal: Boolean): Int {
        val regex = """(\d+),(\d+) -> (\d+),(\d+)""".toRegex()
        val vents = input.map {
            val (x1, y1, x2, y2) = regex.find(it.trim())?.destructured ?: error("Error!")
            Vent(x1.toInt(), y1.toInt(), x2.toInt(), y2.toInt())
        }

        val maxX = vents.maxOf { maxOf(it.x1, it.x2) }
        val maxY = vents.maxOf { maxOf(it.y1, it.y2) }

        val points = Array(maxX + 1) { Array(maxY + 1) { 0 } }

        for (vent in vents) {
            if (vent.x1 == vent.x2) {
                for (i in minOf(vent.y1, vent.y2)..maxOf(vent.y1, vent.y2)) {
                    points[vent.x1][i]++
                }
            }
            if (vent.y1 == vent.y2) {
                for (i in minOf(vent.x1, vent.x2)..maxOf(vent.x1, vent.x2)) {
                    points[i][vent.y1]++
                }
            }
            if (considerDiagonal) {
                if ((vent.x1 - vent.x2).absoluteValue == (vent.y1 - vent.y2).absoluteValue) {
                    if (((vent.x1 < vent.x2) && (vent.y1 < vent.y2)) ||
                        ((vent.x1 > vent.x2) && (vent.y1 > vent.y2))
                    ) {
                        for (i in 0..(vent.x1 - vent.x2).absoluteValue) {
                            points[minOf(vent.x1, vent.x2) + i][minOf(vent.y1, vent.y2) + i]++
                        }
                    } else {
                        for (i in 0..(vent.x1 - vent.x2).absoluteValue) {
                            points[maxOf(vent.x1, vent.x2) - i][minOf(vent.y1, vent.y2) + i]++
                        }
                    }
                }
            }
        }

        return points.sumOf { it -> it.count {it > 1}}
    }

    fun part1(input: List<String>): Int {
        return process(input, false)
    }

    fun part2(input: List<String>): Int {
        return process(input, true)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    var result = part1(input)
    println(result)
    check(result == 6113)
    result = part2(input)
    println(result)
    check(result == 20373)
}