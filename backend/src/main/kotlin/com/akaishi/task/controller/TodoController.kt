package com.akaishi.task.controller

import com.akaishi.task.entity.Todo
import com.akaishi.task.service.TodoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class TodoController {

    @Autowired
    lateinit var todoService: TodoService

    @GetMapping("/todos")
    fun getTodos(): List<Todo> {
        return todoService.getTodos()
    }
}