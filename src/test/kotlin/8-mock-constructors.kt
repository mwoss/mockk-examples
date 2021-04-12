import io.mockk.every
import io.mockk.mockkConstructor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FooWithConstructor(private val x: Int = 0) {

    init {
        println("Initializing FooWithConstructor")
        println("Value of $x")
    }

    fun sum(a: Int) = a + x
}

class MockConstructorTest {

    @Test
    fun `this test shows example of constructor mocking`() {
        mockkConstructor(FooWithConstructor::class)

        every { anyConstructed<FooWithConstructor>().sum(1) } returns 4

        assertEquals(4, FooWithConstructor().sum(1)) // newly created object here
    }
}
