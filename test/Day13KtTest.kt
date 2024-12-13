import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class Day13KtTest {
    @Test
    fun day13part01_example() {
        assertEquals(480, day13part01(Path("resources/day13.example.txt")))
    }

    @Test
    fun day13part01() {
        println("Day 13 Part 01 is ${day13part01(Path("resources/day13.input.txt"))}")
    }

    @Test
    fun day13part02() {
        println("Day 13 Part 02 is ${day13part02(Path("resources/day13.input.txt"))}")
    }
}
