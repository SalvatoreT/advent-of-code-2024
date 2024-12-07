import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class Day02KtTest {
    @Test
    fun day02part01_example() {
        assertEquals(2, day02part01(Path("resources/day02.example.txt")))
    }

    @Test
    fun day02part01() {
        println("Day 02 Part 01 is ${day02part01(Path("resources/day02.input.txt"))}")
    }

    @Test
    fun day02part02_example() {
        assertEquals(4, day02part02(Path("resources/day02.example.txt")))
    }

    @Test
    fun day02part02() {
        println("Day 02 Part 02 is ${day02part02(Path("resources/day02.input.txt"))}")
    }
}
