import java.nio.file.Path
import kotlin.io.path.readLines

fun day05part1(path: Path): Int {
    val input = path.readLines()
    val (rawOrders, rawUpdates) =
        input
            .filter { string -> string != "" }
            .partition { string -> string.contains("|") }
    val orders =
        rawOrders
            .map { order -> order.split("|") }
            .groupBy({ it[0].toInt() }, { it[1].toInt() })
    val updates =
        rawUpdates
            .map { orders -> orders.split(",").map { it.toInt() } }
            .filter { updates ->
                isValidUpdate(updates, orders)
            }
    return updates.sumOf { update -> update[(update.size / 2)] }
}

fun day05part2(path: Path): Int {
    val input = path.readLines()
    val (rawOrders, rawUpdates) =
        input
            .filter { string -> string != "" }
            .partition { string -> string.contains("|") }
    val orders =
        rawOrders
            .map { order -> order.split("|") }
            .groupBy({ it[0].toInt() }, { it[1].toInt() })
    val updates =
        rawUpdates
            .map { orders -> orders.split(",").map { it.toInt() } }
            .filter { updates ->
                !isValidUpdate(updates, orders)
            }
    val newUpdates =
        updates.map { update ->
            var new = ""
        }
    return newUpdates.sumOf { update -> update[(update.size / 2)] }
}

private fun isValidUpdate(
    updates: List<Int>,
    orders: Map<Int, List<Int>>,
): Boolean =
    updates.withIndex().all { (index, left) ->
        updates.reversed().take(updates.size - (index + 1)).all { right ->
            orders[right]?.contains(left) != true
        }
    }
