import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class Day01KtTest {
    @Test
    fun day01part01_example() {
        assertEquals(11, day01part01(Path("resources/day01.example.txt")))
    }

    @Test
    fun day01part01() {
        println("Day 01 Part 01 is ${day01part01(Path("resources/day01.input.txt"))}")
    }

    @Test
    fun day01part02_example() {
        assertEquals(31, day01part02(Path("resources/day01.example.txt")))
    }

    @Test
    fun day01part02() {
        println("Day 01 Part 02 is ${day01part02(Path("resources/day01.input.txt"))}")
    }
}
