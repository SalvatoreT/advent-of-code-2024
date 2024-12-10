import java.math.BigInteger
import java.nio.file.Path
import kotlin.collections.toMutableList
import kotlin.io.path.readLines

private sealed class Item {
    data class File(
        val id: Int,
    ) : Item()

    object FreeSpace : Item()
}

fun day09part01(path: Path): BigInteger {
    var disk =
        path
            .readLines()
            .first()
            .toCharArray()
            .flatMapIndexed<Item> { index, character ->
                if (index % 2 == 0) {
                    List<Item>(character.digitToInt()) { Item.File(id = index / 2) }
                } else {
                    List<Item>(character.digitToInt()) { Item.FreeSpace }
                }
            }.toMutableList()
    while (disk.contains(Item.FreeSpace)) {
        val last = disk.removeLast()
        if (last != Item.FreeSpace) {
            val freeIndex = disk.indexOfFirst { it == Item.FreeSpace }
            disk[freeIndex] = last
        }
    }
    return disk
        .filterIsInstance<Item.File>()
        .mapIndexed<Item.File, BigInteger> { index, file -> index.toBigInteger() * file.id.toBigInteger() }
        .reduce { acc, value -> acc + value }
}

private sealed class Item2 {
    data class File(
        val id: Int,
        val size: Int,
    ) : Item2()

    data class FreeSpace(
        val size: Int,
    ) : Item2()
}

fun day09part02(path: Path): BigInteger {
    var disk =
        path
            .readLines()
            .first()
            .toCharArray()
            .mapIndexed<Item2> { index, character ->
                if (index % 2 == 0) {
                    Item2.File(id = index / 2, size = character.digitToInt())
                } else {
                    Item2.FreeSpace(size = character.digitToInt())
                }
            }.toMutableList()
    var currentFileId =
        disk
            .maxOf { if (it is Item2.File) it.id else 0 }
    while (currentFileId > 0) {
        val currentIndex = disk.indexOfFirst { if (it is Item2.File) it.id == currentFileId else false }
        val currentItem = disk[currentIndex] as Item2.File
        val freeIndex =
            disk.indexOfFirst {
                it is Item2.FreeSpace && it.size >= currentItem.size
            }
        if (freeIndex != -1 && freeIndex < currentIndex) {
            disk.remove(currentItem)
            disk.add(currentIndex, Item2.FreeSpace(currentItem.size))
            val freeSpace = disk.removeAt(freeIndex) as Item2.FreeSpace
            if (currentItem.size == freeSpace.size) {
                disk.add(freeIndex, currentItem)
            } else {
                val smallerFreeSpace = Item2.FreeSpace(freeSpace.size - currentItem.size)
                disk.addAll(freeIndex, listOf(currentItem, smallerFreeSpace))
            }
        }
        currentFileId--
    }
    var checksum = 0.toBigInteger()
    var position = 0
    disk.forEach { item ->
        if (item is Item2.FreeSpace) {
            position += item.size
        }
        if (item is Item2.File) {
            repeat(item.size) {
                checksum += position.toBigInteger() * item.id.toBigInteger()
                position++
            }
        }
    }
    return checksum
}

private fun printDisk(disk: List<Item2>) {
    for (item in disk) {
        if (item is Item2.File) {
            repeat(item.size) {
                print(item.id)
            }
        } else if (item is Item2.FreeSpace) {
            repeat(item.size) {
                print('.')
            }
        }
    }
    println()
}
