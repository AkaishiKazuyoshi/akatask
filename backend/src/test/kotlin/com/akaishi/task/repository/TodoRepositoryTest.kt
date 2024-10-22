package com.akaishi.task.repository

import com.akaishi.task.entity.TodoEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import software.amazon.awssdk.services.dynamodb.model.AttributeValue.fromS
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest


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
                "20240925120000-1234567-abcd-efgh-1234-abcde12345",
                "Hello World",
                "This is dynamo db migration data"
            ), result[0]
        )
    }
}