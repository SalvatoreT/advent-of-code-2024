import java.nio.file.Path
import kotlin.io.path.readLines

fun day10part01(path: Path): Int {
    val input =
        path
            .readLines()
            .map { it.map { it.digitToInt() } }

    val startingPoints =
        input.flatMapIndexed { r, row ->
            row
                .mapIndexed { c, value ->
                    if (value == 0) {
                        Pair(r, c)
                    } else {
                        null
                    }
                }.filterNotNull()
        }
    return startingPoints.sumOf {
        input.score(listOf(it))
    }
}

private fun List<List<Int>>.score(startingPosition: List<Pair<Int, Int>>): Int {
    val nines = startingPosition.count { (r, c) -> this[r][c] == 9 }
    if (nines > 0) {
        return nines
    } else if (startingPosition.isEmpty()) {
        return 0
    }
    val numRows = size
    val numCols = first().size
    val nextPositions =
        startingPosition
            .flatMap { (r, c) ->
                listOf(
                    Pair(r - 1, c),
                    Pair(r + 1, c),
                    Pair(r, c - 1),
                    Pair(r, c + 1),
                ).filter { (nr, nc) ->
                    nr >= 0 &&
                        nr < numRows &&
                        nc >= 0 &&
                        nc < numCols &&
                        this[nr][nc] == (this[r][c] + 1)
                }
            }.toSet()
            .toList()
    return score(nextPositions)
}

fun day10part02(path: Path): Int {
    val input =
        path
            .readLines()
            .map { it.map { it.digitToInt() } }

    val startingPoints =
        input.flatMapIndexed { r, row ->
            row
                .mapIndexed { c, value ->
                    if (value == 0) {
                        Pair(r, c)
                    } else {
                        null
                    }
                }.filterNotNull()
        }
    return startingPoints.sumOf {
        input.growTrails(listOf(listOf(it))).toSet().count()
    }
}

private fun List<List<Int>>.growTrails(trails: List<List<Pair<Int, Int>>>): List<List<Pair<Int, Int>>> {
    if (trails.isEmpty()) {
        return emptyList()
    }
    if (trails.last().last().let { (r, c) -> this[r][c] == 9 }) {
        return trails
    }
    val numRows = size
    val numCols = first().size
    val biggerTrails =
        trails.flatMap { trail ->
            val options =
                trail.last().let { (r, c) ->
                    listOf(
                        Pair(r - 1, c),
                        Pair(r + 1, c),
                        Pair(r, c - 1),
                        Pair(r, c + 1),
                    ).filter { (nr, nc) ->
                        nr >= 0 &&
                            nr < numRows &&
                            nc >= 0 &&
                            nc < numCols &&
                            this[nr][nc] == (this[r][c] + 1)
                    }
                }
            if (options.isNotEmpty()) {
                options.map { trail + it }
            } else {
                listOf()
            }
        }
    return growTrails(biggerTrails)
}
