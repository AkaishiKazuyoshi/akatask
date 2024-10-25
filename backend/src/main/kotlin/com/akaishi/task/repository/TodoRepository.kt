package com.akaishi.task.repository

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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

interface TodoRepository {
    fun findAll(): List<TodoEntity>
    fun findById(id: String): TodoEntity
    fun addTodo(todoEntity: TodoEntity);
}

@Repository
class DynamoDbTodoRepository(
    @Value("\${amazon.dynamodb.table-name}") tableName: String, dynamoDbEnhancedClient: DynamoDbEnhancedClient
) : TodoRepository {
    val todoEntityTable: DynamoDbTable<DynamoDbTodoEntity> by lazy {
        dynamoDbEnhancedClient.table(
            tableName,
            TableSchema.fromBean(DynamoDbTodoEntity::class.java),
        )
    }

    override fun findAll(): List<TodoEntity> {
        return todoEntityTable.query(
            QueryEnhancedRequest.builder().queryConditional(
                QueryConditional.sortBeginsWith(
                    Key.builder().partitionValue("TODO").sortValue("ID").build()

                )
            ).build()
        ).items().map { item -> TodoEntity(item.id, item.title, item.content) }
    }

    override fun findById(id: String): TodoEntity {
        val dynamoDbEntity = todoEntityTable.getItem(
            Key.builder().partitionValue("TODO").sortValue("ID#${id}").build()
        )
        return TodoEntity(
            dynamoDbEntity.id, dynamoDbEntity.title, dynamoDbEntity.content
        )
    }

    override fun addTodo(todoEntity: TodoEntity) {
        val id = "${TimeUtil.currentDateTime()}-${UUID.randomUUID()}"
        todoEntityTable.putItem(
            DynamoDbTodoEntity(
                pk = "TODO",
                sk ="ID#${id}",
                id = id,
                title = todoEntity.title,
                content = todoEntity.content
            )
        )
    }
}

object TimeUtil {
    @JvmStatic
    fun currentDateTime(): String {
        return LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
        )
    }
}