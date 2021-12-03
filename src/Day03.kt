fun main() {
    fun part1(input: List<String>): Int {
        val countOnes = input
            .map { it -> it.toCharArray().map { it.code - '0'.code } }
            .reduce { acc, numbs -> numbs.zip(acc) { a, b -> a + b } }
        val gamma = Integer.parseInt(
            countOnes
                .map { it -> if (it * 2 > input.size) '1' else '0' }
                .joinToString(""), 2)
        val epsilon = Integer.parseInt(
            countOnes
                .map { it -> if (it * 2 > input.size) '0' else '1' }
                .joinToString(""), 2)

        return gamma * epsilon
    }

    fun part2(input: List<String>): Int {
        val inputLen = input[0].length
        var position = 0
        var buf = input.toMutableList()
        while (position < inputLen && buf.size > 1 ) {
            val countOnes = buf.count { it[position].equals('1') }
            buf.retainAll { (countOnes * 2 >= buf.size && it[position].equals('1') ) || (countOnes * 2 < buf.size && it[position].equals('0') ) }
            position++
        }
        val oxygen = buf[0]
        position = 0
        buf = input.toMutableList()
        while (position < inputLen && buf.size > 1 ) {
            val countOnes = buf.count { it[position].equals('1') }
            buf.retainAll { (countOnes * 2 >= buf.size && it[position].equals('0') ) || (countOnes * 2 < buf.size && it[position].equals('1') ) }
            position++
        }
        val co2 = buf[0]
        return Integer.parseInt(oxygen, 2) * Integer.parseInt(co2, 2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    check(part1(input) == 775304)
    check(part2(input) == 1370737)
    println(part1(input))
    println(part2(input))
}