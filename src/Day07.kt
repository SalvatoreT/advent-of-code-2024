import java.math.BigInteger
import java.nio.file.Path
import kotlin.io.path.readLines

fun day07part01(path: Path): BigInteger =
    path
        .readLines()
        .map { line ->
            val (totalRaw, remainingRaw) = line.split(":")
            val total = totalRaw.toBigInteger()
            val numbers = remainingRaw.trim().split(" ").map { it.toBigInteger() }
            Pair(total, numbers)
        }.filter { (total, numbers) -> isValidEquation(total, numbers) }
        .map { (total, _) -> total }
        .reduce { acc, value -> acc + value }

fun isValidEquation(
    total: BigInteger,
    numbers: List<BigInteger>,
): Boolean {
    if (total == BigInteger.ZERO && numbers.isEmpty()) {
        return true
    } else if (total <= BigInteger.ZERO || numbers.isEmpty()) {
        return false
    } else {
        val last = numbers.last()
        val rest = numbers.dropLast(1)
        if (total % last == BigInteger.ZERO && isValidEquation(total / last, rest)) {
            return true
        } else {
            return isValidEquation(total - last, rest)
        }
    }
}

fun day07part02(path: Path): BigInteger =
    path
        .readLines()
        .map { line ->
            val (totalRaw, remainingRaw) = line.split(":")
            val total = totalRaw.toBigInteger()
            val numbers = remainingRaw.trim().split(" ").map { it.toBigInteger() }
            Pair(total, numbers)
        }.filter { (total, numbers) -> isValidEquation2(total, numbers) }
        .map { (total, _) -> total }
        .reduce { acc, value -> acc + value }

fun isValidEquation2(
    total: BigInteger,
    numbers: List<BigInteger>,
): Boolean {
    if (total == BigInteger.ZERO && numbers.isEmpty()) {
        return true
    } else if (total <= BigInteger.ZERO || numbers.isEmpty()) {
        return false
    } else {
        val last = numbers.last()
        val rest = numbers.dropLast(1)
        return if (total % last == BigInteger.ZERO && isValidEquation2(total / last, rest)) {
            true
        } else if (total.toString().endsWith(last.toString()) &&
            isValidEquation2(
                trimBigInteger(total, last),
                rest,
            )
        ) {
            true
        } else {
            isValidEquation2(total - last, rest)
        }
    }
}

private fun trimBigInteger(
    total: BigInteger,
    last: BigInteger,
): BigInteger {
    val remainingNumber = total.toString().removeSuffix(last.toString())
    return if (remainingNumber.isEmpty()) {
        BigInteger.ZERO
    } else {
        remainingNumber.toBigInteger()
    }
}
