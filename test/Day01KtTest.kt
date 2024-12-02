import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class Day01KtTest {
    @Test
    fun day01part1() {
        assertEquals(11, day01part1(Path("resources/day01.example.txt")))
        println("Day 1 Part 1 is ${day01part1(Path("resources/day01.first.txt"))}")
    }

    @Test
    fun day01part2() {
        assertEquals(31, day01part2(Path("resources/day01.example.txt")))
        println("Day 1 Part 2 is ${day01part2(Path("resources/day01.first.txt"))}")
    }
}
