import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class Day07KtTest {
    @Test
    fun day07part01_example() {
        assertEquals(3749.toBigInteger(), day07part01(Path("resources/day07.example.txt")))
    }

    @Test
    fun day07part01() {
        println("Day 07 Part 01 is ${day07part01(Path("resources/day07.input.txt"))}")
    }

    @Test
    fun day07part02_example() {
        assertEquals(11387.toBigInteger(), day07part02(Path("resources/day07.example.txt")))
    }

    @Test
    fun day07part02() {
        println("Day 07 Part 02 is ${day07part02(Path("resources/day07.input.txt"))}")
    }
}
