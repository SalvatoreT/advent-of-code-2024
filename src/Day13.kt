import java.nio.file.Path
import kotlin.io.path.readLines

private val BUTTON_REGEX = """X\+(\d+), Y\+(\d+)""".toRegex()
private val PRIZE_REGEX = """X=(\d+), Y=(\d+)""".toRegex()

private data class Machine(
    val buttonA: Pair<Int, Int>,
    val buttonB: Pair<Int, Int>,
    val prize: Pair<Int, Int>,
) {
    fun minimumTokens(): Int? {
        val (aX, aY) = buttonA
        val (bX, bY) = buttonB
        val (prizeX, prizeY) = prize
        val scores =
            (0..200).flatMap { a -> (0..200).map { b -> a to b } }.mapNotNull { (a, b) ->
                if (a * aX + b * bX == prizeX && a * aY + b * bY == prizeY) {
                    a * 3 + b
                } else {
                    null
                }
            }
        return if (scores.isEmpty()) {
            null
        } else {
            scores.min()
        }
    }
}

fun day13part01(path: Path): Int {
    val input =
        path
            .readLines()
            .chunked(4)
            .map {
                val (rawAX, rawAY) = BUTTON_REGEX.find(it[0])!!.destructured
                val (rawBX, rawBY) = BUTTON_REGEX.find(it[1])!!.destructured
                val (rawPrizeX, rawPrizeY) = PRIZE_REGEX.find(it[2])!!.destructured
                Machine(
                    buttonA = rawAX.toInt() to rawAY.toInt(),
                    buttonB = rawBX.toInt() to rawBY.toInt(),
                    prize = rawPrizeX.toInt() to rawPrizeY.toInt(),
                )
            }
    return input.mapNotNull { it.minimumTokens() }.sum()
}
