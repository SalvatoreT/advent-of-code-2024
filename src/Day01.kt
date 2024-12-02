import java.nio.file.Path
import kotlin.io.path.readLines
import kotlin.math.abs

fun day01part1(path: Path): Int {
    val (left, right) =
        path
            .readLines()
            .map { line -> line.split("   ") }
            .map { (left, right) -> Pair(left.toInt(), right.toInt()) }
            .unzip()
    return left
        .sorted()
        .zip(right.sorted())
        .sumOf { (left, right) -> abs(left - right) }
}

fun day01part2(path: Path): Int {
    val (left, right) =
        path
            .readLines()
            .map { line -> line.split("   ") }
            .map { (left, right) -> Pair(left.toInt(), right.toInt()) }
            .unzip()
    return left.sumOf { l -> l * right.count { r -> l == r } }
}
