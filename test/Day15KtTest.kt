import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class Day15KtTest {
    @Test
    fun day15part01_example() {
        assertEquals(10092, day15part01(Path("resources/day15.example.txt")))
    }

    @Test
    fun day15part01_example2() {
        assertEquals(2028, day15part01(Path("resources/day15.example2.txt")))
    }

    @Test
    fun day15part01() {
        println("Day 15 Part 01 is ${day15part01(Path("resources/day15.input.txt"))}")
    }

    @Test
    fun day15part02_example() {
        assertEquals(9021, day15part02(Path("resources/day15.example.txt")))
    }

    @Test
    fun day15part02() {
        println("Day 15 Part 02 is ${day15part02(Path("resources/day15.input.txt"))}")
    }
}
