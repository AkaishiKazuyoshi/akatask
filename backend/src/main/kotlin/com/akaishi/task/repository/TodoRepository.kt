package com.akaishi.task.repository

import com.akaishi.task.config.DynamoDbConfig
import com.akaishi.task.entity.DynamoDbTodoEntity
import com.akaishi.task.entity.TodoEntity
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest
import software.amazon.awssdk.services.dynamodb.model.QueryRequest

interface TodoRepository {
    fun findAll(): List<TodoEntity>
}

@Repository
class DynamoDbTodoRepository(
    @Value("\${amazon.dynamodb.table-name}") tableName: String,
    dynamoDbEnhancedClient: DynamoDbEnhancedClient
) : TodoRepository {
    val todoEntityTable: DynamoDbTable<DynamoDbTodoEntity> by lazy {
        dynamoDbEnhancedClient.table(
            tableName,
            TableSchema.fromBean(DynamoDbTodoEntity::class.java),
        )
    }

    override fun findAll(): List<TodoEntity> {
       return todoEntityTable.query(
            QueryEnhancedRequest
                .builder()
                .queryConditional(
                    QueryConditional.sortBeginsWith(
                        Key.builder()
                            .partitionValue("TODO")
                            .sortValue("ID")
                            .build()

                    )
                )
                .build()
        ).items()
            .map { item -> TodoEntity(item.id, item.title, item.content) }
    }
}