import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class Day12KtTest {
    @Test
    fun day12part01_example() {
        assertEquals(140, day12part01(Path("resources/day12.example.txt")))
    }

    @Test
    fun day12part01_example2() {
        assertEquals(772, day12part01(Path("resources/day12.example2.txt")))
    }

    @Test
    fun day12part01_example3() {
        assertEquals(1930, day12part01(Path("resources/day12.example3.txt")))
    }

    @Test
    fun day12part01() {
        println("Day 12 Part 01 is ${day12part01(Path("resources/day12.input.txt"))}")
    }

    @Test
    fun day12part02_example() {
        assertEquals(80, day12part02(Path("resources/day12.example.txt")))
    }

    @Test
    fun day12part02_example2() {
        assertEquals(436, day12part02(Path("resources/day12.example2.txt")))
    }

    @Test
    fun day12part02() {
        println("Day 12 Part 02 is ${day12part02(Path("resources/day12.input.txt"))}")
    }
}
