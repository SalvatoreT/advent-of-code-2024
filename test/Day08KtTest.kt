import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class Day08KtTest {
    @Test
    fun day08part01_example() {
        assertEquals(14, day08part01(Path("resources/day08.example.txt")))
    }

    @Test
    fun day08part01() {
        println("Day 08 Part 01 is ${day08part01(Path("resources/day08.input.txt"))}")
    }

    @Test
    fun day08part02_example() {
        assertEquals(34, day08part02(Path("resources/day08.example.txt")))
    }

    @Test
    fun day08part02() {
        println("Day 08 Part 02 is ${day08part02(Path("resources/day08.input.txt"))}")
    }
}
