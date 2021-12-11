data class Location(
    val height: Int
) {
    var visited: Boolean = false
}

fun main() {

    fun part1(input: List<String>): Int {
        val heightmap = input
            .map { it -> it.toCharArray().map { it.code - '0'.code } }
        val rows = heightmap.size
        val cols = heightmap[0].size
        var sum = 0
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (((i == 0) || (heightmap[i - 1][j] > heightmap[i][j])) &&
                    ((i == rows - 1) || (heightmap[i + 1][j] > heightmap[i][j])) &&
                    ((j == 0) || (heightmap[i][j - 1] > heightmap[i][j])) &&
                    ((j == cols - 1) || (heightmap[i][j + 1] > heightmap[i][j]))
                ) {
                    sum += (heightmap[i][j] + 1)
                }
            }
        }
        return sum
    }

    fun getBasinSize(heightmap: List<List<Location>>, i: Int, j: Int): Int {
        var result = 1
        heightmap[i][j].visited = true
        if ((i > 0) && (!heightmap[i - 1][j].visited) && (heightmap[i - 1][j].height < 9)) {
            result += getBasinSize(heightmap, i - 1, j)
        }
        if ((i < heightmap.size - 1) && (!heightmap[i + 1][j].visited) && (heightmap[i + 1][j].height < 9)) {
            result += getBasinSize(heightmap, i + 1, j)
        }
        if ((j > 0) && (!heightmap[i][j - 1].visited) && (heightmap[i][j - 1].height < 9)) {
            result += getBasinSize(heightmap, i, j - 1)
        }
        if ((j < heightmap[0].size - 1) && (!heightmap[i][j + 1].visited) && (heightmap[i][j + 1].height < 9)) {
            result += getBasinSize(heightmap, i, j + 1)
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val heightmap = input
            .map { it -> it.toCharArray().map { Location(it.code - '0'.code) } }
        val rows = heightmap.size
        val cols = heightmap[0].size
        val sizes = mutableListOf<Int>()
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (((i == 0) || (heightmap[i - 1][j].height > heightmap[i][j].height)) &&
                    ((i == rows - 1) || (heightmap[i + 1][j].height > heightmap[i][j].height)) &&
                    ((j == 0) || (heightmap[i][j - 1].height > heightmap[i][j].height)) &&
                    ((j == cols - 1) || (heightmap[i][j + 1].height > heightmap[i][j].height))
                ) {
                    val size = getBasinSize(heightmap, i, j)
                    if (sizes.size < 3) {
                        sizes.add(size)
                    } else if (size > sizes.minOf { it }) {
                        sizes.remove(sizes.minOf { it })
                        sizes.add(size)
                    }
                }
            }
        }
        return sizes.reduce { acc, i -> acc * i }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 1134)

    val input = readInput("Day09")
    var result = part1(input)
    println(result)
    check(result == 526)
    result = part2(input)
    println(result)
    check(result == 1123524)
}