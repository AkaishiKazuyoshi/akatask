package com.akaishi.task.service

import com.akaishi.task.entity.Todo
import com.akaishi.task.repository.TodoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface TodoService {
    fun getTodos(): List<Todo>
}

@Service
class DefaultTodoService(val todoRepository: TodoRepository) : TodoService {
    override fun getTodos(): List<Todo> {
        val todos = todoRepository.findAll()
        return todos.map { todo ->
            Todo(id = todo.id, title = todo.title, content = todo.content)
        }
    }
}