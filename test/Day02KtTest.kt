import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class Day02KtTest {
    @Test
    fun day02part1_example() {
        assertEquals(2, day02part1(Path("resources/day02.example.txt")))
    }

    @Test
    fun day02part1() {
        println("Day 2 Part 1 is ${day02part1(Path("resources/day02.input.txt"))}")
    }

    @Test
    fun day02part2_example() {
        assertEquals(4, day02part2(Path("resources/day02.example.txt")))
    }

    @Test
    fun day02part2() {
        println("Day 2 Part 2 is ${day02part2(Path("resources/day02.input.txt"))}")
    }
}
