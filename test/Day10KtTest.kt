import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class Day10KtTest {
    @Test
    fun day10part01_example() {
        assertEquals(36, day10part01(Path("resources/day10.example.txt")))
    }

    @Test
    fun day10part01() {
        println("Day 10 Part 01 is ${day10part01(Path("resources/day10.input.txt"))}")
    }

    @Test
    fun day10part02_example() {
        assertEquals(81, day10part02(Path("resources/day10.example.txt")))
    }

    @Test
    fun day10part02() {
        println("Day 10 Part 02 is ${day10part02(Path("resources/day10.input.txt"))}")
    }
}
