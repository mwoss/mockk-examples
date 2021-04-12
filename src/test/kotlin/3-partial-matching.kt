import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals


// List of matchers can be found here https://mockk.io/#matchers
// Matchers can be implemented from scratch too

class FooPartial {
    fun complexMethod(a: Int, b: Int, c: String, d: Double?): String {
        println("Doing something")
        return "LOL XD"
    }
}

class PartialMatchingTest {
    @Test
    fun `this test shows example of partial matching`() {
        val foo = mockk<FooPartial>()

        every { foo.complexMethod(less(100), more(10), any(), isNull()) } returns "Mocked :3"

        assertEquals(foo.complexMethod(50, 20, "ABCD", null), "Mocked :3")
    }
}
