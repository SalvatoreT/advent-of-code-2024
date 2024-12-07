import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class Day07KtTest {
    @Test
    fun day07part1_example() {
        assertEquals(3749.toBigInteger(), day07part1(Path("resources/day07.example.txt")))
    }

    @Test
    fun day07part1() {
        println("Day 07 Part 1 is ${day07part1(Path("resources/day07.input.txt"))}")
    }

    @Test
    fun day07part2_example() {
        assertEquals(11387.toBigInteger(), day07part2(Path("resources/day07.example.txt")))
    }

    @Test
    fun day07part2() {
        println("Day 07 Part 2 is ${day07part2(Path("resources/day07.input.txt"))}")
    }
}
