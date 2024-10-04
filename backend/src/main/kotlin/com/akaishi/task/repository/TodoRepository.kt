package com.akaishi.task.repository

import com.akaishi.task.entity.TodoEntity
import org.springframework.stereotype.Repository

interface TodoRepository {
    fun findAll(): List<TodoEntity>
}

@Repository
class DynamoDbTodoRepository : TodoRepository {
    override fun findAll(): List<TodoEntity> {
        TODO("Not yet implemented")
    }
}