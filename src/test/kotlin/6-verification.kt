import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

// List of validators can be found here https://mockk.io/#validators

class FooVerify {
    fun subtract(a: Int, b: Int): Int = a - b
}

class VerifyTest {
    @Test
    fun `this test shows example for simple mockk verification`(){
        val foo = mockk<FooVerify>(relaxed = true)

        foo.subtract(5, 10)
        foo.subtract(100, 100)
        foo.subtract(0 , 0)

        verify(atLeast = 3) { foo.subtract(any(), any()) }
        verify(atLeast = 2) { foo.subtract(more(0), more(0)) }
        verify(exactly = 1) { foo.subtract(100, 100) }
    }

    @Test
    fun `this test shows example for simple timeout verification`(){
        val foo = mockk<FooVerify>(relaxed = true)

        Thread {
            Thread.sleep(1000)
            foo.subtract(100, 100)
        }.start()

        verify(timeout = 2000) {
            // it waits until verification is passed or timeout is reached
            foo.subtract(any(), any())
        }
    }
}
