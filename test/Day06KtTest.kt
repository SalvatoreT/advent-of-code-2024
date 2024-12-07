import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class Day06KtTest {
    @Test
    fun day06part01_example() {
        assertEquals(41, day06part01(Path("resources/day06.example.txt")))
    }

    @Test
    fun day06part01() {
        println("Day 06 Part 01 is ${day06part01(Path("resources/day06.input.txt"))}")
    }

    @Test
    fun day06part02_example() {
        assertEquals(6, day06part02(Path("resources/day06.example.txt")))
    }

    @Test
    fun day06part02() {
        println("Day 06 Part 02 is ${day06part02(Path("resources/day06.input.txt"))}")
    }
}
