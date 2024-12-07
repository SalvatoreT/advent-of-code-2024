import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class Day05KtTest {
    @Test
    fun day05part01_example() {
        assertEquals(143, day05part01(Path("resources/day05.example.txt")))
    }

    @Test
    fun day05part01() {
        println("Day 05 Part 01 is ${day05part01(Path("resources/day05.input.txt"))}")
    }

    @Test
    fun day05part02_example() {
        assertEquals(123, day05part02(Path("resources/day05.example.txt")))
    }

    @Test
    fun day05part02() {
        println("Day 05 Part 02 is ${day05part02(Path("resources/day05.input.txt"))}")
    }
}
