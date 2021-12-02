import kotlin.math.max

fun main() {
    fun part1(input: List<String>): Int {
        var depth = 0
        var position = 0
        for(inputLine in input) {
            val sanitized : String = inputLine.trim()
            if(sanitized.isNotEmpty()) {
                val regex: Regex = """(\D+)(\s+)(\d+)""".toRegex()
                val matchResult = regex.find(sanitized)
                val (command, _, arg) = matchResult!!.destructured
                val units : Int = arg.toInt()
                when (command) {
                    "forward" -> position += units
                    "down" -> depth += units
                    "up" -> depth = max(0, depth - units)
                }
            }
        }
        return position * depth
    }

    fun part2(input: List<String>): Int {
        var depth = 0
        var position = 0
        var aim = 0
        for(inputLine in input) {
            val sanitized : String = inputLine.trim()
            if(sanitized.isNotEmpty()) {
                val regex: Regex = """(\D+)(\s+)(\d+)""".toRegex()
                val matchResult = regex.find(sanitized)
                val (command, _, arg) = matchResult!!.destructured
                val units : Int = arg.toInt()
                when (command) {
                    "forward" -> {
                        position += units
                        depth += (aim * units)
                    }
                    "down" -> aim += units
                    "up" -> aim = max(0, aim - units)
                }
            }
        }
        return position * depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}