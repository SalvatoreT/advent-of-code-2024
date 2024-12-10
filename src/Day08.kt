import java.nio.file.Path
import kotlin.io.path.readLines

private data class Antenna(
    val type: Char,
    val location: Pair<Int, Int>,
)

fun day08part01(path: Path): Int {
    val input = path.readLines()
    val numRows = input.size
    val numColumns = input.first().length
    val antennas =
        input
            .flatMapIndexed { rowIndex, rowString ->
                rowString.mapIndexed { colIndex, type -> Antenna(type, Pair(rowIndex.toInt(), colIndex.toInt())) }
            }.filter { it.type != '.' }
    val groups = antennas.groupBy { it.type }

    fun isInBounds(point: Pair<Int, Int>): Boolean =
        point.first >= 0 && point.second >= 0 && point.first < numRows && point.second < numColumns
    val antinodes =
        groups.values
            .flatMap { antennaList ->
                antennaList.flatMapIndexed { index, (_, start) ->
                    antennaList
                        .takeLast(antennaList.size - (index + 1))
                        .flatMap { (_, end) ->
                            val delta = end - start
                            listOf(start - delta, end + delta)
                        }
                }
            }.filter { isInBounds(it) }
    return antinodes.toSet().count()
}

fun day08part02(path: Path): Int {
    val input = path.readLines()
    val numRows = input.size
    val numColumns = input.first().length
    val antennas =
        input
            .flatMapIndexed { rowIndex, rowString ->
                rowString.mapIndexed { colIndex, type -> Antenna(type, Pair(rowIndex.toInt(), colIndex.toInt())) }
            }.filter { it.type != '.' }
    val groups = antennas.groupBy { it.type }

    fun isInBounds(point: Pair<Int, Int>): Boolean =
        point.first >= 0 && point.second >= 0 && point.first < numRows && point.second < numColumns

    val antinodes =
        groups.values
            .flatMap { antennaList ->
                antennaList.flatMapIndexed { index, (_, start) ->
                    antennaList
                        .takeLast(antennaList.size - (index + 1))
                        .flatMap { (_, end) ->
                            val delta = end - start
                            var newNodes = mutableListOf<Pair<Int, Int>>(start, end)
                            var currentNode = start - delta
                            while (isInBounds(currentNode)) {
                                newNodes += currentNode
                                currentNode -= delta
                            }
                            currentNode = end + delta
                            while (isInBounds(currentNode)) {
                                newNodes += currentNode
                                currentNode += delta
                            }
                            newNodes
                        }
                }
            }.toSet()
    return antinodes.count()
}

private operator fun Pair<Int, Int>.minus(other: Pair<Int, Int>) = Pair(first - other.first, second - other.second)

private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>) = Pair(first + other.first, second + other.second)
