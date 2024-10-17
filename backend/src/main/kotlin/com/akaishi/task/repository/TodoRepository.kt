package com.akaishi.task.repository

import com.akaishi.task.config.DynamoDbConfig
import com.akaishi.task.entity.TodoEntity
import org.springframework.stereotype.Repository

interface TodoRepository {
    fun findAll(): List<TodoEntity>
}

@Repository
class DynamoDbTodoRepository : TodoRepository {
    override fun findAll(): List<TodoEntity> {

        val config = DynamoDbConfig("aaa")
        val client = config.dynamoDbClient()

        TODO("Not yet implemented")
    }
}