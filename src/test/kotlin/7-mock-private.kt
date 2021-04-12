import io.mockk.every
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

// You should avoid mocking private methods, but if you have to then...

class PrivateFoo {
    fun doSomething(a: String) = helper(a)

    private fun helper(a: String) = "Just string"
}

class PrivateMethodMockTest {
    @Test
    fun `this test shows how to mock private method`() {
        val mock = spyk<PrivateFoo>(recordPrivateCalls = true)

        every { mock["helper"](any<String>()) } returns "Something new"

        assertEquals(mock.doSomething("XD"), "Something new")

        verify {
            mock["helper"](any<String>())
        }
    }
}
