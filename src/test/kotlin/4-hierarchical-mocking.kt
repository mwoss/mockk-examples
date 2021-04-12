import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

/**
 * Mocking ElasticSearch clients is a great example of hierarchical mocking :3
 *
 * val indicesClient = mockk<IndicesClient> {
 *  every { create(any<CreateIndexRequest>(), any()) }
 *      .returns(mockk {})
 *      .andThenThrows(ElasticsearchActivityFailedException("resource_already_exists_exception"))
 * }
 * every { mockElasticsearchClient.indices() } returns indicesClient
 */

data class Person(
    val name: String,
    val surname: String,
    val age: Int,
    val address: Address
)

data class Address(
    val city: String,
    val street: String,
    val country: String
)

class HierarchicalMocking {
    @Test
    fun `this test shows example of hierarchical mocking`() {
        val person = mockk<Person> {
            every { address } returns mockk {
                every { city } returns "Warsaw"
                every { street } returns "Dluga"
                every { country } returns "Poland"
            }
        }

        val city = person.address.city

        assertEquals("Warsaw", city)
    }
}
