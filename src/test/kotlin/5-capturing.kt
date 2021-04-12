import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class FooCapture {
    fun add(a: Int, b: Int): Int = a + b
}

class CaptureTest {
    @Test
    fun `this test shows example for argument capturing`(){
        val foo = mockk<FooCapture>()
        val slotA = slot<Int>()

        every {
            foo.add(capture(slotA), more(5))
        } returns 10

        val sum = foo.add(4, 10)

        println(slotA)

        assertEquals(10, sum)
    }
}
