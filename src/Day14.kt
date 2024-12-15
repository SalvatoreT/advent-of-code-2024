import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.readLines

private data class Robot(
    var position: Pair<Int, Int>,
    val velocity: Pair<Int, Int>,
) {
    fun tick(
        seconds: Int,
        dimensions: Pair<Int, Int>,
    ) {
        val (gridX, gridY) = dimensions
        val (px, py) = position
        val (vx, vy) = velocity
        val newX = (((px + vx * seconds) % gridX) + gridX) % gridX
        val newY = (((py + vy * seconds) % gridY) + gridY) % gridY
        position = newX to newY
    }

    companion object {
        val regex = """p=(-?\d+),(-?\d+)\s+v=(-?\d+),(-?\d+)""".toRegex()

        fun fromString(input: String): Robot {
            val (px, py, vx, vy) = regex.matchEntire(input)!!.destructured
            return Robot(px.toInt() to py.toInt(), vx.toInt() to vy.toInt())
        }
    }
}

fun day14part01(
    path: Path,
    dimensions: Pair<Int, Int>,
): Int {
    val middleX = (dimensions.first - 1) / 2
    val middleY = (dimensions.second - 1) / 2
    val input =
        path
            .readLines()
            .map { Robot.fromString(it) }
    return input
        .onEach { it.tick(100, dimensions) }
        .filterNot { it.position.first == middleX || it.position.second == middleY }
        .groupBy {
            val (x, y) = it.position
            when {
                x < middleX && y < middleY -> 1
                x < middleX && y > middleY -> 2
                x > middleX && y < middleY -> 3
                x > middleX && y > middleY -> 4
                else -> throw Exception("Invalid $it")
            }
        }.map { (_, value) -> value.count() }
        .reduce { acc, value -> acc * value }
}

fun day14part02(
    path: Path,
    dimensions: Pair<Int, Int>,
) {
    val tempFile = Files.createTempFile("day14.output", ".txt")

    val input =
        path
            .readLines()
            .map { Robot.fromString(it) }

    tempFile.toFile().printWriter().use { writer ->
        for (i in (0..10_000)) {
            writer.println("-----------")
            writer.println("seconds=$i")
            val positions = input.map { it.position }.toSet()
            for (x in (0 until dimensions.first)) {
                for (y in (0 until dimensions.second)) {
                    if (positions.contains(x to y)) {
                        writer.print('#')
                    } else {
                        writer.print('.')
                    }
                }
                writer.println()
            }
            writer.println()
            writer.println()
            input.forEach { it.tick(1, dimensions) }
        }
    }

    println("Output written to temporary file: $tempFile")
}
