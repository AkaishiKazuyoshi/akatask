package com.akaishi.task.repository

import com.akaishi.task.entity.TodoEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import software.amazon.awssdk.services.dynamodb.model.AttributeValue.fromS
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest
import java.util.*


@SpringBootTest
class TodoRepositoryTest : DynamoDbTestConfig() {
    @Autowired
    lateinit var sut: TodoRepository

    @BeforeEach
    fun setUp() {
        clearDatabase()
    }

    @Test
    fun findAll() {
        dynamoDbClient.putItem(
            PutItemRequest.builder().tableName(TEST_TABLE_NAME).item(
                mapOf(
                    "PK" to fromS("TODO"),
                    "SK" to fromS("ID#20240925120000-1234567-abcd-efgh-1234-abcde12345"),
                    "id" to fromS("20240925120000-1234567-abcd-efgh-1234-abcde12345"),
                    "title" to fromS("Hello World"),
                    "content" to fromS("This is dynamo db migration data"),
                )
            ).build()
        )


        val result = sut.findAll()


        assertEquals(1, result.size)
        assertEquals(
            TodoEntity(
                "20240925120000-1234567-abcd-efgh-1234-abcde12345", "Hello World", "This is dynamo db migration data"
            ), result[0]
        )
    }

    @Test
    fun findById() {
        dynamoDbClient.putItem(
            PutItemRequest.builder().tableName(TEST_TABLE_NAME).item(
                mapOf(
                    "PK" to fromS("TODO"),
                    "SK" to fromS("ID#20240925120000-1234567-abcd-efgh-1234-abcde12345"),
                    "id" to fromS("20240925120000-1234567-abcd-efgh-1234-abcde12345"),
                    "title" to fromS("Hello World"),
                    "content" to fromS("This is dynamo db migration data"),
                )
            ).build()
        )


        val result = sut.findById("20240925120000-1234567-abcd-efgh-1234-abcde12345")


        assertEquals(
            TodoEntity(
                "20240925120000-1234567-abcd-efgh-1234-abcde12345", "Hello World", "This is dynamo db migration data"
            ), result
        )
    }

    @Test
    fun addTodo() {
        val uuid = UUID.randomUUID()
        val timeStub = Mockito.mockStatic(TimeUtil::class.java)
        timeStub.`when`<String> { TimeUtil.currentDateTime() }.thenReturn("20240925120000")
        val uuidStub = Mockito.mockStatic(UUID::class.java)
        uuidStub.`when`<UUID> { UUID.randomUUID() }.thenReturn(uuid)


        sut.addTodo(
            TodoEntity(
                "", "Hello World", "This is dynamo db migration data"
            )
        )


        val result = dynamoDbClient.getItem(
            GetItemRequest.builder().tableName(TEST_TABLE_NAME).key(
                mapOf(
                    "PK" to fromS("TODO"),
                    "SK" to fromS("ID#20240925120000-${uuid}"),
                )
            ).build()
        ).item()
        assertEquals("20240925120000-${uuid}", result["id"]!!.s())
        assertEquals("Hello World", result["title"]!!.s())
        assertEquals("This is dynamo db migration data", result["content"]!!.s())
        timeStub.close()
        uuidStub.close()
    }
}