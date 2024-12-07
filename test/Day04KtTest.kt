import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class Day04KtTest {
    @Test
    fun day04part01_example() {
        assertEquals(18, day04part01(Path("resources/day04.example.txt")))
    }

    @Test
    fun day04part01() {
        println("Day 04 Part 01 is ${day04part01(Path("resources/day04.input.txt"))}")
    }

    @Test
    fun day04part02_example() {
        assertEquals(9, day04part02(Path("resources/day04.example.txt")))
    }

    @Test
    fun day04part02() {
        println("Day 04 Part 02 is ${day04part02(Path("resources/day04.input.txt"))}")
    }
}
