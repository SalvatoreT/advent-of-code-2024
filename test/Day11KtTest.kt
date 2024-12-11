import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class Day11KtTest {
    @Test
    fun day11part01_example() {
        assertEquals(55312.toBigInteger(), day11part01(Path("resources/day11.example.txt"), 25))
    }

    @Test
    fun day11part01() {
        println("Day 11 Part 01 is ${day11part01(Path("resources/day11.input.txt"), 25)}")
    }

    @Test
    fun day11part02() {
        println("Day 11 Part 02 is ${day11part01(Path("resources/day11.input.txt"), 75)}")
    }
}
