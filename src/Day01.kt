fun main() {
    fun part1(input: List<String>): Int {
        var previousMeasurement = Int.MAX_VALUE
        var result = 0
        for(inputLine in input) {
            val sanitized : String = inputLine.trim()
            val measurement : Int = sanitized.toInt()
            if(measurement > previousMeasurement) result++
            previousMeasurement = measurement
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val previousMeasurement = arrayOf(Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE)
        var previousMeasurementPosition = 0
        var result = 0
        for(inputLine in input) {
            val sanitized : String = inputLine.trim()
            val measurement : Int = sanitized.toInt()
            if(measurement > previousMeasurement[previousMeasurementPosition]) result++
            previousMeasurement[previousMeasurementPosition] = measurement
            previousMeasurementPosition = (previousMeasurementPosition + 1) % 3
        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
