import java.math.BigInteger
import java.nio.file.Path
import kotlin.collections.reduce
import kotlin.io.path.readLines

fun day11part01(
    path: Path,
    rounds: Int,
): BigInteger {
    val input =
        path
            .readLines()
            .first()
            .split(" ")
    var counts = input.associateWith { 1.toBigInteger() }
    repeat(rounds) {
        var newCounts = mutableMapOf<String, BigInteger>()
        counts.forEach { (stone, count) ->
            blink(stone).forEach { nextStone ->
                val current = newCounts.getOrPut(nextStone) { 0.toBigInteger() }
                newCounts[nextStone] = current + count
            }
        }
        counts = newCounts
    }
    return counts.values.reduce { acc, value -> acc + value }
}

private fun blink(stone: String): List<String> =
    when {
        stone == "0" -> listOf("1")
        stone.length % 2 == 0 -> listOf(stone.take(stone.length / 2), stone.takeLast(stone.length / 2).toBigInteger().toString())
        else -> listOf((stone.toBigInteger() * 2024.toBigInteger()).toString())
    }
