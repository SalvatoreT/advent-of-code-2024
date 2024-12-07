import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class Day03KtTest {
    @Test
    fun day03part01_example() {
        assertEquals(161, day03part01(Path("resources/day03.example.txt")))
    }

    @Test
    fun day03part01() {
        println("Day 03 Part 01 is ${day03part01(Path("resources/day03.input.txt"))}")
    }

    @Test
    fun day03part02_example() {
        assertEquals(48, day03part02(Path("resources/day03.example2.txt")))
    }

    @Test
    fun day03part02() {
        println("Day 03 Part 02 is ${day03part02(Path("resources/day03.input.txt"))}")
    }
}
