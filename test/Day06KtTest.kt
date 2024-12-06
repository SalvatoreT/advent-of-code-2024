import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class Day06KtTest {
    @Test
    fun day06part1_example() {
        assertEquals(41, day06part1(Path("resources/day06.example.txt")))
    }

    @Test
    fun day06part1() {
        println("Day 06 Part 1 is ${day06part1(Path("resources/day06.input.txt"))}")
    }

    @Test
    fun day06part2_example() {
        assertEquals(6, day06part2(Path("resources/day06.example.txt")))
    }

    @Test
    fun day06part2() {
        println("Day 06 Part 2 is ${day06part2(Path("resources/day06.input.txt"))}")
    }
}
