package com.akaishi.task.service

import com.akaishi.task.entity.Todo
import org.springframework.stereotype.Service

@Service
interface TodoService {
    fun getTodos(): List<Todo>
}