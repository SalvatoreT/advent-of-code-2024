import java.nio.file.Path
import kotlin.io.path.readLines

data class Region(
    val label: Char,
    val lots: MutableSet<Pair<Int, Int>>,
) {
    fun area(): Int = lots.size

    fun parameter(): Int =
        lots.sumOf { (r, c) ->
            neighbors(r, c).count { !lots.contains(it) }
        }

    fun sides(): Int =
        listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))
            .sumOf { (deltaR, deltaC) ->
                var shadows =
                    lots
                        .map { (r, c) -> Pair(r + deltaR, c + deltaC) }
                        .filter { !lots.contains(it) }
                        .toMutableSet()
                var shadowGroups = mutableListOf<Set<Pair<Int, Int>>>()
                while (!shadows.isEmpty()) {
                    val start = shadows.take(1).first()
                    shadows.remove(start)
                    val shadowGroup = mutableSetOf(start)
                    do {
                        val startingShadowGroupSize = shadowGroup.count()
                        val neighborShadows =
                            shadowGroup
                                .flatMap { neighbors(it) }
                                .filter { shadows.contains(it) }
                                .toSet()
                        shadowGroup.addAll(neighborShadows)
                        shadows.removeAll(neighborShadows)
                    } while (startingShadowGroupSize != shadowGroup.count())
                    shadowGroups.add(shadowGroup)
                }
                shadowGroups.count()
            }
}

fun day12part01(path: Path): Int {
    val input =
        path
            .readLines()
            .map { it.toCharArray().toList() }
    val numRows = input.size
    val numColumns = input.first().size
    var undeclared = (0..<numRows).flatMap { row -> List(numColumns) { row }.zip(0..<numColumns) }.toMutableSet()
    var regions = mutableListOf<Region>()
    while (!undeclared.isEmpty()) {
        val start = undeclared.take(1).first()
        val (r, c) = start
        undeclared.remove(start)
        val region = Region(label = input[r][c], lots = mutableSetOf(start))
        do {
            val startingRegionSize = region.lots.size
            val newLots =
                region.lots
                    .flatMap { (r, c) ->
                        neighbors(r, c)
                            .filter { undeclared.contains(it) }
                            .filter { (r, c) -> input[r][c] == region.label }
                    }.toSet()
            region.lots.addAll(newLots)
            undeclared.removeAll(newLots)
        } while (startingRegionSize != region.lots.size)
        regions.add(region)
    }
    return regions.sumOf { it.area() * it.parameter() }
}

private fun neighbors(
    r: Int,
    c: Int,
): List<Pair<Int, Int>> = listOf(Pair(r - 1, c), Pair(r + 1, c), Pair(r, c - 1), Pair(r, c + 1))

private fun neighbors(start: Pair<Int, Int>): List<Pair<Int, Int>> = neighbors(start.first, start.second)

fun day12part02(path: Path): Int {
    val input =
        path
            .readLines()
            .map { it.toCharArray().toList() }
    val numRows = input.size
    val numColumns = input.first().size
    var undeclared = (0..<numRows).flatMap { row -> List(numColumns) { row }.zip(0..<numColumns) }.toMutableSet()
    var regions = mutableListOf<Region>()
    while (!undeclared.isEmpty()) {
        val start = undeclared.take(1).first()
        val (r, c) = start
        undeclared.remove(start)
        val region = Region(label = input[r][c], lots = mutableSetOf(start))
        do {
            val startingRegionSize = region.lots.size
            val newLots =
                region.lots
                    .flatMap { (r, c) ->
                        neighbors(r, c)
                            .filter { undeclared.contains(it) }
                            .filter { (r, c) -> input[r][c] == region.label }
                    }.toSet()
            region.lots.addAll(newLots)
            undeclared.removeAll(newLots)
        } while (startingRegionSize != region.lots.size)
        regions.add(region)
    }
    return regions.sumOf { it.area() * it.sides() }
}
