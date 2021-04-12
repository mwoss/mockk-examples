import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.spyk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

class FooCalculator {
    fun sum(a: Int, b: Int): Int = a + b
}


class GenericFooSomething<T> {
    fun returnValue(a: T): T = a
}

class UnitClass {
    fun foo() {
        println("Something something")
    }
}


class MockTypesTest {

    @Test
    fun `this test shows example of MockK class`() {
        val calculator = mockk<FooCalculator>()
        every { calculator.sum(any(), any()) } returns 5
        // every { calculator.sum(any(), any()) } returns 3

        val sum = calculator.sum(1, 2)

        assertEquals(3, sum)
    }

    @Test
    fun `this test shows example of Spy class`() {
        val calculator = spyk<FooCalculator>()  // copy of a passed object
        //every { calculator.sum(any(), any()) } returns 5

        val sum = calculator.sum(1, 2)

        assertEquals(3, sum)
    }

    @Test
    fun `this test shows example of relaxed mock`() {
        val calculator = mockk<FooCalculator>(relaxed = true)

        val sum = calculator.sum(1, 2)

        assertEquals(3, sum)
    }

    @Test
    fun `this test shows example of relaxed mock on generic return type`() {
        val something = mockk<GenericFooSomething<Int>>(relaxed = true)

        // Generic functions cannot be mocked as simple as normal function, but there is a workaround :3
        // val something = mockk<() -> GenericFooSomething<Int>>(relaxed = true)
        // every { something() } returns GenericFooSomething()

        val value = something.returnValue(5)
        // val value = something().returnValue(5)

        assertEquals(5, value)
    }

    @Test
    fun `this test shows example of relaxed unit mock`() {
        val unit = mockk<UnitClass>(relaxUnitFun = true)

        val result = unit.foo()

        assertEquals(Unit, result)
    }
}


@ExtendWith(MockKExtension::class)
class MockkAnnotationsTest {
    @MockK(name = "ICanBeDebuggedEasierWithIt", relaxed = false, relaxUnitFun = false)
    lateinit var fooMockk: FooCalculator

    @RelaxedMockK
    lateinit var fooRelaxed: FooCalculator

    @SpyK
    var fooSpy: FooCalculator = FooCalculator()

    @Test
    fun `test mocks via annotations`() {
        val result = fooRelaxed.sum(1, 2)
        Assertions.assertEquals(3, result)
    }
}

