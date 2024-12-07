import java.nio.file.Path
import kotlin.io.path.readLines

fun day03part01(path: Path): Int =
    path
        .readLines()
        .sumOf { line -> scores(line) }

private fun scores(input: String): Int {
    val regex = Regex("""mul\((?<first>[1-9]\d{0,2}),\s*(?<second>[1-9]\d{0,2})\)""")

    return regex
        .findAll(input)
        .map { match ->
            val first = match.groups["first"]?.value!!.toInt()
            val second = match.groups["second"]?.value!!.toInt()
            first * second
        }.sum()
}

fun day03part02(path: Path): Int =
    path
        .readLines()
        .joinToString()
        .let { line -> removeOffCommands(line) }
        .let { line -> scores(line) }

private fun removeOffCommands(input: String): String {
    val regex = Regex("""don't\(\).*?(do\(\)|$)""")
    return input.replace(regex, "")
}
