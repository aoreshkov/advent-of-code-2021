fun main() {

    fun getScore(char: Char?): Int {
        return when (char) {
            ')' -> 3
            ']' -> 57
            '}' -> 1197
            '>' -> 25137
            else -> 0
        }
    }

    fun part1(input: List<String>): Int {
        val lines = input.map { it.trim().toCharArray() }
        val stack = ArrayDeque<Char>()
        var result = 0
        for (line in lines) {
            for (char in line) {
                when (char) {
                    '(', '[', '{', '<' -> stack.addLast(char)
                    ')', ']', '}', '>' -> {
                        val oldChar = stack.removeLastOrNull()
                        when (char) {
                            ')' -> if (oldChar != '(') {
                                result += getScore(char)
                                break
                            }
                            ']' -> if (oldChar != '[') {
                                result += getScore(char)
                                break
                            }
                            '}' -> if (oldChar != '{') {
                                result += getScore(char)
                                break
                            }
                            '>' -> if (oldChar != '<') {
                                result += getScore(char)
                                break
                            }
                        }
                    }
                }

            }
        }
        return result
    }

    fun getNewScore(char: Char): Int {
        return when (char) {
            '(' -> 1
            '[' -> 2
            '{' -> 3
            '<' -> 4
            else -> 0
        }
    }

    fun part2(input: List<String>): Long {
        val lines = input.map { it.trim().toCharArray() }
        val stack = ArrayDeque<Char>()
        val scores: MutableList<Long> = mutableListOf()
        var isBroken = false
        for (line in lines) {
            stack.clear()
            for (char in line) {
                when (char) {
                    '(', '[', '{', '<' -> stack.addLast(char)
                    ')', ']', '}', '>' -> {
                        val oldChar = stack.removeLastOrNull()
                        when (char) {
                            ')' -> if (oldChar != '(') {
                                isBroken = true
                                break
                            }
                            ']' -> if (oldChar != '[') {
                                isBroken = true
                                break
                            }
                            '}' -> if (oldChar != '{') {
                                isBroken = true
                                break
                            }
                            '>' -> if (oldChar != '<') {
                                isBroken = true
                                break
                            }
                        }
                    }
                }

            }
            if (!isBroken) {
                var score = 0L
                while (stack.isNotEmpty()) {
                    val char = stack.removeLast()
                    score = (score * 5) + getNewScore(char)
                }
                scores.add(score)
            }
            isBroken = false
        }
        return scores.sorted().elementAt(scores.size / 2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 26397)
    check(part2(testInput) == 288957L)

    val input = readInput("Day10")
    val result1 = part1(input)
    println(result1)
    check(result1 == 341823)
    val result2 = part2(input)
    println(result2)
    check(result2 == 2801302861L)
}