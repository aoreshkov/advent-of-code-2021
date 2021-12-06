data class Board(val cells: List<Int>) {
    var rows: Array<Int> = arrayOf(0, 0, 0, 0, 0)
    var cols: Array<Int> = arrayOf(0, 0, 0, 0, 0)
    var sum: Int = 0
    var win: Boolean = false
}

fun main() {

    fun process(input: List<String>, squidWin: Boolean): Int {
        val numbers = input[0].trim().split(",").map { it.toInt() }
        val boards = input.subList(2, input.size).map { it.trim() }.windowed(5, 6)
            .map { Board(it.joinToString(" ").split("\\D+".toRegex()).map { value -> value.toInt() }) }
        var count = 0

        for (number in numbers) {
            for (board in boards) {
                val index = board.cells.indexOf(number)
                if (index >= 0) {
                    val row = index / 5
                    val col = index % 5
                    board.rows[row]++
                    board.cols[col]++
                    board.sum += number
                    if (board.rows[row] == 5 || board.cols[col] == 5) {
                        if (!squidWin) {
                            return number * (board.cells.sumOf { it } - board.sum)
                        } else if (!board.win) {
                            board.win = true
                            count++
                            if (count == boards.size) {
                                return number * (board.cells.sumOf { it } - board.sum)
                            }
                        }
                    }
                }
            }
        }
        return 0
    }

    fun part1(input: List<String>): Int {
        return process(input, false)
    }

    fun part2(input: List<String>): Int {
        return process(input, true)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    check(part1(input) == 55770)
    println(part2(input))
    check(part2(input) == 2980)
}