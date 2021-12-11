data class Notes(
    val patterns: List<String>,
    val digits: List<String>
)

fun main() {

    fun part1(input: List<String>): Int {
        var count = 0
        val notes = input.map {
            it.trim().split('|').map { l -> l.trim().split(' ').map { x -> x.toCharArray().sorted().joinToString("") } }
        }.map { it -> Notes(it[0], it[1]) }
        for (note in notes) {
            for (digit in note.digits) {
                if (digit.length in listOf(2, 3, 4, 7)) count++
            }
        }
        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0
        val notes = input.map {
            it.trim().split('|').map { l -> l.trim().split(' ').map { x -> x.toCharArray().sorted().joinToString("") } }
        }.map { it -> Notes(it[0], it[1]) }
        for (note in notes) {
            val trans: MutableMap<String, Int> = mutableMapOf()
            val digits: MutableMap<Int, String> = mutableMapOf()
            val size5: MutableList<String> = mutableListOf()
            val size6: MutableList<String> = mutableListOf()
            for (pattern in note.patterns) {
                when (pattern.length) {
                    2 -> {
                        trans[pattern] = 1
                        digits[1] = pattern
                    }
                    3 -> {
                        trans[pattern] = 7
                        digits[7] = pattern
                    }
                    4 -> {
                        trans[pattern] = 4
                        digits[4] = pattern
                    }
                    7 -> {
                        trans[pattern] = 8
                        digits[8] = pattern
                    }
                    5 -> {
                        size5.add(pattern)
                    }
                    6 -> {
                        size6.add(pattern)
                    }
                }
            }
            for (s in size5) {
                if ((digits[7]!!.toCharArray().toSet() - s.toCharArray().toSet()).isEmpty()) {
                    trans[s] = 3
                    digits[3] = s
                }
            }
            size5.remove(digits[3])
            for (s in size6) {
                if ((digits[7]!!.toCharArray().toSet() - s.toCharArray().toSet()).isNotEmpty()) {
                    trans[s] = 6
                    digits[6] = s
                }
            }
            size6.remove(digits[6])
            for (s in size6) {
                if ((digits[3]!!.toCharArray().toSet() - s.toCharArray().toSet()).isEmpty()) {
                    trans[s] = 9
                    digits[9] = s
                }
            }
            size6.remove(digits[9])
            trans[size6[0]] = 0
            digits[0] = size6[0]
            for (s in size5) {
                if ((s.toCharArray().toSet() - digits[6]!!.toCharArray().toSet()).isEmpty()) {
                    trans[s] = 5
                    digits[5] = s
                }
            }
            size5.remove(digits[5])
            trans[size5[0]] = 2
            digits[2] = size5[0]
            val result = note.digits.map { it -> trans[it] }.joinToString("").toInt()
            count += result
        }
        return count
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput("Day08")
    var result = part1(input)
    println(result)
    check(result == 554)
    result = part2(input)
    println(result)
    check(result == 990964)
}