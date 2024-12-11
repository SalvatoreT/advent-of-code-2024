import java.nio.file.Path
import kotlin.io.path.readLines

fun day11part01(
    path: Path,
    rounds: Int,
): Int {
    val input =
        path
            .readLines()
            .first()
            .split(" ")
    var stones = input
    var index = 0
    repeat(rounds) {
//        println("round: ${index++}")
        stones = morphStones(stones)
    }
    return stones.count()
}

fun morphStones(stones: List<String>): List<String> =
    stones
        .flatMap { stone ->
            when {
                stone == "0" -> listOf("1")
                stone.length % 2 == 0 -> listOf(stone.take(stone.length / 2), stone.takeLast(stone.length / 2))
                else -> listOf((stone.toBigInteger() * 2024.toBigInteger()).toString())
            }
        }.map { it.toBigInteger().toString() }
