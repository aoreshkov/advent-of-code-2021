import kotlin.math.absoluteValue

private enum class ProcessingType {
    SIMPLE,
    COMPLEX
}

fun main() {

    fun getFuel(position:Int, type: ProcessingType): Int {
        return when(type) {
            ProcessingType.SIMPLE -> position
            ProcessingType.COMPLEX -> position * (position + 1) / 2
        }
    }

    fun process(input: List<String>, type: ProcessingType): Int {
        val positions = input[0].split(',').map { it.toInt() }.groupingBy { it }.eachCount()
        val minPosition = positions.keys.minOf { it }
        val maxPosition = positions.keys.maxOf { it }
        var minTotal = Int.MAX_VALUE

        for (i in minPosition .. maxPosition) {
            var total = 0
            positions.forEach{total += it.value * getFuel((it.key - i).absoluteValue, type)}
            if (total < minTotal) minTotal = total
        }

        return minTotal
    }

    fun part1(input: List<String>): Int {
        return process(input, ProcessingType.SIMPLE)
    }

    fun part2(input: List<String>): Int {
        return process(input, ProcessingType.COMPLEX)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("Day07")
    var result = part1(input)
    println(result)
    check(result == 328318)
    result = part2(input)
    println(result)
    check(result == 89791146)
}