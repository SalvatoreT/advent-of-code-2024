import java.nio.file.Path
import kotlin.io.path.readLines

sealed class WarehouseObject(
    var position: Pair<Int, Int>,
) {
    abstract fun push(
        direction: Pair<Int, Int>,
        warehouse: Map<Pair<Int, Int>, WarehouseObject>,
    ): List<WarehouseObject>

    class Wall(
        position: Pair<Int, Int>,
    ) : WarehouseObject(position) {
        override fun push(
            direction: Pair<Int, Int>,
            warehouse: Map<Pair<Int, Int>, WarehouseObject>,
        ): List<WarehouseObject> = listOf()
    }

    class Box(
        position: Pair<Int, Int>,
    ) : WarehouseObject(position) {
        override fun push(
            direction: Pair<Int, Int>,
            warehouse: Map<Pair<Int, Int>, WarehouseObject>,
        ): List<WarehouseObject> {
            val (r, c) = position
            val (dR, dC) = direction
            val neighbor = warehouse[r + dR to c + dC]
            if (neighbor == null) {
                return listOf(this)
            } else {
                val next = neighbor.push(direction, warehouse)
                return if (next.isEmpty()) {
                    next
                } else {
                    next + listOf(this)
                }
            }
        }
    }

    class Robot(
        position: Pair<Int, Int>,
    ) : WarehouseObject(position) {
        override fun push(
            direction: Pair<Int, Int>,
            warehouse: Map<Pair<Int, Int>, WarehouseObject>,
        ): List<WarehouseObject> {
            val (r, c) = position
            val (dR, dC) = direction
            val neighbor = warehouse[r + dR to c + dC]
            if (neighbor == null) {
                return listOf(this)
            } else {
                val next = neighbor.push(direction, warehouse)
                return if (next.isEmpty()) {
                    next
                } else {
                    next + listOf(this)
                }
            }
        }
    }
}

fun day15part01(path: Path): Int {
    val input = path.readLines()
    val rawMap = input.takeWhile { it.isNotEmpty() }
    val rawDirections = input.takeLast(input.count() - 1 - rawMap.count())

    var warehouse =
        rawMap
            .flatMapIndexed { rowNum, row ->
                row.mapIndexed { colNum, char ->
                    val warehouseObject =
                        when (char) {
                            '#' -> WarehouseObject.Wall(rowNum to colNum)
                            'O' -> WarehouseObject.Box(rowNum to colNum)
                            '@' -> WarehouseObject.Robot(rowNum to colNum)
                            else -> null
                        }
                    if (warehouseObject == null) {
                        null
                    } else {
                        (rowNum to colNum) to warehouseObject
                    }
                }
            }.filterNotNull()
            .toMap()
            .toMutableMap()

    val directions =
        rawDirections
            .joinToString("")
            .map {
                when (it) {
                    '^' -> (-1 to 0)
                    'v' -> (1 to 0)
                    '<' -> (0 to -1)
                    '>' -> (0 to 1)
                    else -> throw Exception("Invalid direction $it")
                }
            }

    val robot = warehouse.values.first { it is WarehouseObject.Robot }
    directions.forEach { direction ->
        robot
            .push(direction, warehouse)
            .onEach { warehouse.remove(it.position) }
            .onEach { it.position = it.position + direction }
            .onEach { warehouse.put(it.position, it) }
    }

    return warehouse.values
        .filter { it is WarehouseObject.Box }
        .sumOf { it.position.first * 100 + it.position.second }
}

sealed class WideWarehouseObject(
    var positions: List<Pair<Int, Int>>,
) {
    abstract fun push(
        direction: Pair<Int, Int>,
        warehouse: Map<Pair<Int, Int>, WideWarehouseObject>,
    ): List<WideWarehouseObject>

    class Wall(
        positions: List<Pair<Int, Int>>,
    ) : WideWarehouseObject(positions) {
        override fun push(
            direction: Pair<Int, Int>,
            warehouse: Map<Pair<Int, Int>, WideWarehouseObject>,
        ): List<WideWarehouseObject> = emptyList()
    }

    class Box(
        positions: List<Pair<Int, Int>>,
    ) : WideWarehouseObject(positions) {
        override fun push(
            direction: Pair<Int, Int>,
            warehouse: Map<Pair<Int, Int>, WideWarehouseObject>,
        ): List<WideWarehouseObject> {
            val neighbors =
                positions.map { position ->
                    val (r, c) = position
                    val (dR, dC) = direction
                    if (positions.contains(r + dR to c + dC)) {
                        null
                    } else {
                        warehouse[r + dR to c + dC]
                    }
                }
            if (neighbors.all { it == null }) {
                return listOf(this)
            } else {
                val next =
                    neighbors
                        .filterNotNull()
                        .map { neighbor -> neighbor.push(direction, warehouse) }
                return if (next.any { it.isEmpty() }) {
                    emptyList()
                } else {
                    (next.flatten() + listOf(this)).toSet().toList()
                }
            }
        }
    }

    class Robot(
        positions: List<Pair<Int, Int>>,
    ) : WideWarehouseObject(positions) {
        override fun push(
            direction: Pair<Int, Int>,
            warehouse: Map<Pair<Int, Int>, WideWarehouseObject>,
        ): List<WideWarehouseObject> {
            val neighbors =
                positions.map { position ->
                    val (r, c) = position
                    val (dR, dC) = direction
                    if (positions.contains(r + dR to c + dC)) {
                        null
                    } else {
                        warehouse[r + dR to c + dC]
                    }
                }
            if (neighbors.all { it == null }) {
                return listOf(this)
            } else {
                val next =
                    neighbors
                        .filterNotNull()
                        .map { neighbor -> neighbor.push(direction, warehouse) }
                return if (next.any { it.isEmpty() }) {
                    emptyList()
                } else {
                    (next.flatten() + listOf(this)).toSet().toList()
                }
            }
        }
    }
}

fun day15part02(path: Path): Int {
    val input = path.readLines()
    val rawMap = input.takeWhile { it.isNotEmpty() }
    val numRows = rawMap.count()
    val numColumns = rawMap.first().length
    val rawDirections = input.takeLast(input.count() - 1 - rawMap.count())

    var warehouse =
        rawMap
            .flatMapIndexed { rowNum, row ->
                row.mapIndexed { baseColNum, char ->
                    val startColNum = baseColNum * 2
                    val warehouseObject =
                        when (char) {
                            '#' -> WideWarehouseObject.Wall(listOf(rowNum to startColNum, rowNum to startColNum + 1))
                            'O' -> WideWarehouseObject.Box(listOf(rowNum to startColNum, rowNum to startColNum + 1))
                            '@' -> WideWarehouseObject.Robot(listOf(rowNum to startColNum))
                            else -> null
                        }
                    if (warehouseObject == null) {
                        null
                    } else {
                        if (warehouseObject is WideWarehouseObject.Robot) {
                            listOf((rowNum to startColNum) to warehouseObject)
                        } else {
                            listOf((rowNum to startColNum) to warehouseObject, (rowNum to startColNum + 1) to warehouseObject)
                        }
                    }
                }
            }.filterNotNull()
            .flatten()
            .toMap()
            .toMutableMap()

    val directions =
        rawDirections
            .joinToString("")
            .map {
                when (it) {
                    '^' -> (-1 to 0)
                    'v' -> (1 to 0)
                    '<' -> (0 to -1)
                    '>' -> (0 to 1)
                    else -> throw Exception("Invalid direction $it")
                }
            }

    val robot = warehouse.values.first { it is WideWarehouseObject.Robot }
    directions.forEachIndexed { iteration, direction ->
        robot
            .push(direction, warehouse)
            .onEach { it.positions.forEach { warehouse.remove(it) } }
            .onEach { it.positions = it.positions.map { it + direction } }
            .onEach { it.positions.forEach { position -> warehouse.put(position, it) } }
//        println()
//        println("-----------------")
//        println("iteration=$iteration after attempting=$direction")
//        printWarehouse(warehouse, numRows, numColumns)
    }

    return warehouse.values
        .filter { it is WideWarehouseObject.Box }
        .toSet()
        .sumOf { it.positions.first().first * 100 + it.positions.first().second }
}

private fun printWarehouse(
    warehouse: Map<Pair<Int, Int>, WideWarehouseObject>,
    numRows: Int,
    numColumns: Int,
) {
    for (r in 0..<numRows) {
        for (c in 0..<(numColumns * 2)) {
            val item = warehouse[r to c]
            val symbol =
                when (item) {
                    is WideWarehouseObject.Box -> {
                        if (item.positions.first() == r to c) {
                            '['
                        } else {
                            ']'
                        }
                    }
                    is WideWarehouseObject.Wall -> '#'
                    is WideWarehouseObject.Robot -> '@'
                    else -> '.'
                }
            print(symbol)
        }
        println()
    }
}

private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>) = Pair(first + other.first, second + other.second)
