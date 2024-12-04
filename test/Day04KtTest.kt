import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class Day04KtTest {
    @Test
    fun day04part1_example() {
        assertEquals(18, day04part1(Path("resources/day04.example.txt")))
    }

    @Test
    fun day04part1() {
        println("Day 04 Part 1 is ${day04part1(Path("resources/day04.input.txt"))}")
    }

    @Test
    fun day04part2_example() {
        assertEquals(9, day04part2(Path("resources/day04.example.txt")))
    }

    @Test
    fun day04part2() {
        println("Day 04 Part 2 is ${day04part2(Path("resources/day04.input.txt"))}")
    }
}
