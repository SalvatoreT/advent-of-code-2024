import java.math.BigInteger
import java.nio.file.Path
import kotlin.io.path.readLines

private val BUTTON_REGEX = """X\+(\d+), Y\+(\d+)""".toRegex()
private val PRIZE_REGEX = """X=(\d+), Y=(\d+)""".toRegex()

private data class Machine(
    val buttonA: Pair<Int, Int>,
    val buttonB: Pair<Int, Int>,
    val prize: Pair<Int, Int>,
) {
    // A`aX` + B`bX` = `prizeX`
    // A`aY` + B`bY` = `prizeY`
    // rearranged
    // A = (`prizeX``bY` - `prizeY``bX`) / (`aX``bY` - `bX``aY`)
    // B = (`aX``prizeY` - `prizeX``aY`) / (`aX``bY` - `bX``aY`)
    fun minimumTokens(): Int? {
        val (aX, aY) = buttonA
        val (bX, bY) = buttonB
        val (prizeX, prizeY) = prize
        val aMultiple = (prizeX * bY - prizeY * bX) / (aX * bY - bX * aY)
        val bMultiple = (aX * prizeY - prizeX * aY) / (aX * bY - bX * aY)
        return if (aMultiple * aX + bMultiple * bX == prizeX && aMultiple * aY + bMultiple * bY == prizeY) {
            aMultiple * 3 + bMultiple
        } else {
            null
        }
    }

    fun minimumTokensBig(): BigInteger? {
        val (aX, aY) = buttonA.let { (x, y) -> x.toBigInteger() to y.toBigInteger() }
        val (bX, bY) = buttonB.let { (x, y) -> x.toBigInteger() to y.toBigInteger() }
        val (prizeX, prizeY) =
            prize.let { (x, y) ->
                x.toBigInteger() + 10000000000000.toBigInteger() to
                    y.toBigInteger() + 10000000000000.toBigInteger()
            }
        val aMultiple = (prizeX * bY - prizeY * bX) / (aX * bY - aY * bX)
        val bMultiple = (aX * prizeY - aY * prizeX) / (aX * bY - aY * bX)
        return if (aMultiple * aX + bMultiple * bX == prizeX && aMultiple * aY + bMultiple * bY == prizeY) {
            aMultiple * 3.toBigInteger() + bMultiple
        } else {
            null
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

fun day13part02(path: Path): BigInteger {
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
    return input.mapNotNull { it.minimumTokensBig() }.reduce { acc, num -> acc + num }
}
