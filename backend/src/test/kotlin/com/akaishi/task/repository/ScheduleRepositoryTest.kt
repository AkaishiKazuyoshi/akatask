package com.akaishi.task.repository

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ScheduleRepositoryTest: DynamoDbTestConfig() {

    @BeforeEach
    fun setUp() {
        clearDatabase()
    }

    @Test
    fun bar() {
        print("aaa")
    }
}