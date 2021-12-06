fun main() {

    fun process(input: List<String>, days: Int): Long {
        val ages = input[0].split(',').map { it.toInt() }.groupingBy { it }.eachCount().mapValues { it.value.toLong() }
            .toMutableMap()
        for (i in 0..8) {
            ages.putIfAbsent(i, 0)
        }
        for (day in 1..days) {
            val zeroes = ages[0]!!
            for (i in 1..8) {
                ages[i - 1] = ages[i]!!
            }
            ages[6] = ages[6]!! + zeroes
            ages[8] = zeroes
        }

        return ages.values.sum()
    }

    fun part1(input: List<String>): Long {
        return process(input, 80)
    }

    fun part2(input: List<String>): Long {
        return process(input, 256)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934L)
    check(part2(testInput) == 26984457539L)

    val input = readInput("Day06")
    var result = part1(input)
    println(result)
    check(result == 343441L)
    result = part2(input)
    println(result)
    check(result == 1569108373832L)
}