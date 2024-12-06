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
                                '^' -> Tile.Guard
                                '#' -> Tile.Obstacle
                                else -> throw Exception("Unexpected '$symbol' symbol")
                            }
                        if (tile == Tile.Guard) {
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

fun day06part2(path: Path): Int {
    val visited = mutableSetOf<Pair<Int, Int>>()
    var startingVelocity = Pair(-1, 0)
    var startingPosition = Pair(0, 0)
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
                                '^' -> Tile.Guard
                                '#' -> Tile.Obstacle
                                else -> throw Exception("Unexpected '$symbol' symbol")
                            }
                        if (symbol == '^') {
                            startingPosition = Pair(row, column)
                        }
                        Pair(column, tile)
                    }.toMap()
                    .let { Pair(row, it) }
            }.toMap()
    return floor
        .map { (row, rowMap) ->
            rowMap.count { (column, tile) ->
                if (tile == Tile.Empty) {
                    val newRow = rowMap.toMutableMap().also { it[column] = Tile.Obstacle }
                    val newFloor = floor.toMutableMap().also { it[row] = newRow }
                    infiniteLoops(newFloor, startingPosition, startingVelocity)
                } else {
                    false
                }
            }
        }.sum()
}

private fun infiniteLoops(
    floor: Map<Int, Map<Int, Tile>>,
    startingPosition: Pair<Int, Int>,
    startingVelocity: Pair<Int, Int>,
): Boolean {
    var position = startingPosition
    var velocity = startingVelocity
    var visited = mutableListOf<Pair<Int, Int>>()
    while (floor[position.first]?.contains(position.second) == true) {
        visited += position
        if (visited.size > 16900) {
            val (lastLeft, lastRight) = visited.takeLast(2)
            val occurrences =
                visited
                    .windowed(2)
                    .count { (sampleLeft, sampleRight) -> sampleLeft == lastLeft && sampleRight == lastRight }
            return occurrences > 1
        }
        while (floor[position.first + velocity.first]?.get(position.second + velocity.second) == Tile.Obstacle) {
            velocity = nextVelocity(velocity)
        }
        position = Pair(position.first + velocity.first, position.second + velocity.second)
    }
    return false
}

private fun nextVelocity(velocity: Pair<Int, Int>): Pair<Int, Int> =
    when (velocity) {
        Pair(-1, 0) -> Pair(0, 1)
        Pair(0, 1) -> Pair(1, 0)
        Pair(1, 0) -> Pair(0, -1)
        Pair(0, -1) -> Pair(-1, 0)
        else -> throw Exception("Unexpected velocity $velocity")
    }
