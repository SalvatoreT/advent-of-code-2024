import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class Day14KtTest {
    @Test
    fun day14part01_example() {
        assertEquals(12, day14part01(Path("resources/day14.example.txt"), 11 to 7))
    }

    @Test
    fun day14part01() {
        println("Day 14 Part 01 is ${day14part01(Path("resources/day14.input.txt"), 101 to 103)}")
    }

    @Test
    fun day14part02() {
        println("Day 14 Part 02 written to file.")
        day14part02(Path("resources/day14.input.txt"), 101 to 103)
        println("Search file for a bunch of #'s in a row.")
        println("The answer is 8050.")
    }
}
