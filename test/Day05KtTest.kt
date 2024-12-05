import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class Day05KtTest {
    @Test
    fun day05part1_example() {
        assertEquals(143, day05part1(Path("resources/day05.example.txt")))
    }

    @Test
    fun day05part1() {
        println("Day 05 Part 1 is ${day05part1(Path("resources/day05.input.txt"))}")
    }

    @Test
    fun day05part2_example() {
        assertEquals(123, day05part2(Path("resources/day05.example.txt")))
    }

    @Test
    fun day05part2() {
        println("Day 05 Part 2 is ${day05part2(Path("resources/day05.input.txt"))}")
    }
}
