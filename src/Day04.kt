import java.nio.file.Path
import kotlin.io.path.readLines
import kotlin.math.max

fun day04part1(path: Path): Int {
    val input = path.readLines()
    return listOf(
        input,
        rotate90(input),
        rotate90(rotate90(input)),
        rotate90(rotate90(rotate90(input))),
    ).sumOf { countXmas(it) + countXmasDiagonal(it) }
}

fun day04part2(path: Path): Int {
    val input = path.readLines()
    return listOf(
        input,
        rotate90(input),
        rotate90(rotate90(input)),
        rotate90(rotate90(rotate90(input))),
    ).sumOf { countXmasGrid(it) }
}

fun countXmasGrid(input: List<String>): Int =
    input.windowed(3).sumOf { rows ->
        (0..<rows.first().length)
            .windowed(3)
            .count { (col0, col1, col2) ->
                rows[0][col0] == 'M' &&
                    rows[0][col2] == 'S' &&
                    rows[1][col1] == 'A' &&
                    rows[2][col0] == 'M' &&
                    rows[2][col2] == 'S'
            }
    }

private fun rotate90(input: List<String>): List<String> {
    val rows = input.size
    val cols = input.firstOrNull()?.length ?: 0
    val output = Array(cols) { CharArray(rows) }
    input.forEachIndexed { rowIndex, line ->
        line.forEachIndexed { colIndex, character ->
            output[colIndex][cols - rowIndex - 1] = character
        }
    }
    return output.map { it.joinToString("") }
}

private fun countXmas(input: List<String>): Int {
    val regex = Regex("""XMAS""")
    return input.sumOf { regex.findAll(it).count() }
}

private fun countXmasDiagonal(input: List<String>): Int {
    val rows = input.size
    val cols = input.firstOrNull()?.length ?: 0
    return (0..<input.size + cols)
        .map { diagonal ->
            (max(rows - diagonal, 0)..<rows)
                .zip((max(diagonal - rows, 0)..<cols))
                .map { (r, c) -> input[r][c] }
                .joinToString("")
        }.let { countXmas(it) }
}
