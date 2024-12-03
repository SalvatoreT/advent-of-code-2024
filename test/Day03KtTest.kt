import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class Day03KtTest {
    @Test
    fun day03part1_example() {
        assertEquals(161, day03part1(Path("resources/day03.example.txt")))
    }

    @Test
    fun day03part1() {
        println("Day 3 Part 1 is ${day03part1(Path("resources/day03.input.txt"))}")
    }

    @Test
    fun day03part2_example() {
        assertEquals(48, day03part2(Path("resources/day03.example2.txt")))
    }

    @Test
    fun day03part2() {
        println("Day 3 Part 2 is ${day03part2(Path("resources/day03.input.txt"))}")
    }
}
