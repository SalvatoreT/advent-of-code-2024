import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class Day09KtTest {
    @Test
    fun day09part01_example() {
        assertEquals(1928.toBigInteger(), day09part01(Path("resources/day09.example.txt")))
    }

    @Test
    fun day09part01() {
        println("Day 09 Part 01 is ${day09part01(Path("resources/day09.input.txt"))}")
    }

    @Test
    fun day09part02_example() {
        assertEquals(2858.toBigInteger(), day09part02(Path("resources/day09.example.txt")))
    }

    @Test
    fun day09part02() {
        println("Day 09 Part 02 is ${day09part02(Path("resources/day09.input.txt"))}")
    }
}
