import java.nio.file.Path
import kotlin.io.path.readLines

enum class Tile {
    Empty,
    Obstacle,
    Guard,
}

fun day06part1(path: Path): Int {
    val visited = mutableSetOf<Pair<Int, Int>>()
    var velocity = Pair(-1, 0)
    var currentPosition = Pair(0, 0)
    val floor =
        path
            .readLines()
            .mapIndexed { row, line ->
                line
                    .toCharArray()
                    .mapIndexed { column, symbol ->
                        val tile =
                            when (symbol) {
                                '.' -> Tile.Empty
                                '^' -> Tile.Empty
                                '#' -> Tile.Obstacle
                                else -> throw Exception("Unexpected '$symbol' symbol")
                            }
                        if (symbol == '^') {
                            visited += Pair(row, column)
                            currentPosition = Pair(row, column)
                        }
                        Pair(column, tile)
                    }.toMap()
                    .let { Pair(row, it) }
            }.toMap()
    while (floor[currentPosition.first]?.contains(currentPosition.second) == true) {
        visited += currentPosition
        while (floor[currentPosition.first + velocity.first]?.get(currentPosition.second + velocity.second) == Tile.Obstacle) {
            velocity =
                when (velocity) {
                    Pair(-1, 0) -> Pair(0, 1)
                    Pair(0, 1) -> Pair(1, 0)
                    Pair(1, 0) -> Pair(0, -1)
                    Pair(0, -1) -> Pair(-1, 0)
                    else -> throw Exception("Unexpected velocity $velocity")
                }
        }
        currentPosition = Pair(currentPosition.first + velocity.first, currentPosition.second + velocity.second)
    }
    return visited.size
}
