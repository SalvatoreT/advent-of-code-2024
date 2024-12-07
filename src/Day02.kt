import java.nio.file.Path
import kotlin.io.path.readLines
import kotlin.math.abs

fun day02part01(path: Path): Int =
    path
        .readLines()
        .map { line -> line.split(' ') }
        .map { numbers -> numbers.map { it.toInt() } }
        .count { readings -> isSafe(readings) }

fun day02part02(path: Path): Int =
    path
        .readLines()
        .map { line -> line.split(' ') }
        .map { numbers -> numbers.map { it.toInt() } }
        .count { readings ->
            (0..readings.size - 1)
                .toList()
                .any { index -> isSafe(readings.filterIndexed { i, _ -> i != index }) }
        }

private fun isSafe(readings: List<Int>): Boolean {
    val difference = readings.windowed(2).map { (first, second) -> first - second }
    if (difference.any { abs(it) < 1 || abs(it) > 3 }) {
        return false
    }
    if (difference.all { it > 0 } || difference.all { it < 0 }) {
        return true
    }
    return false
}
