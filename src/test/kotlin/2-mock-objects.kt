import io.mockk.every
import io.mockk.mockkObject
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import io.mockk.unmockkObject
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import java.time.LocalDateTime

object Foo {
    fun currentDate(): String {
        return "2020-03-30"
    }
}

enum class BarEnum {
    XD,
    LOL,
}

@TestInstance(Lifecycle.PER_CLASS)
class MockObjectsTest {

    @BeforeAll
    fun setUp() {
        mockkObject(Foo)

        mockkStatic(LocalDateTime::class)
        every { LocalDateTime.now() } returns LocalDateTime.MIN

        mockkObject(BarEnum.XD)
    }

    @AfterAll
    fun tearDown() {
        unmockkObject(Foo)
        unmockkAll()
    }

    @Test
    fun `this test shows example of object mocking`() {
        every { Foo.currentDate() } returns "XDDD"

        val result = Foo.currentDate()

        assertEquals("XDDD", result)
    }

    @Test
    fun `this test shows example of java static class mocking`() {
        val currentDate = LocalDateTime.now()

        assertEquals(LocalDateTime.of(2020, 3, 30, 12, 0, 0), currentDate)
    }

    @Test
    fun `this test shows example of enum mocking`() {
        every { BarEnum.XD.name } returns "Not XD"

        assertEquals(BarEnum.XD.name, "XD")
    }
}
