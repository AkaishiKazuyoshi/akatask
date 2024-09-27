package com.akaishi.task.service

import com.akaishi.task.entity.TodoEntity
import com.akaishi.task.repository.TodoRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class TodoServiceTests {

    lateinit var sut: TodoService

    lateinit var todoRepository: TodoRepository

    @BeforeEach
    fun setUp() {
        todoRepository = mock(TodoRepository::class.java)
        sut = DefaultTodoService(todoRepository)
    }

    @Test
    fun `return Todo list when getTodos was called`() {
        `when`(todoRepository.findAll()).thenReturn(
            listOf(
                TodoEntity(
                    id = 1,
                    title = "title1"
                ),
                TodoEntity(
                    id = 2,
                    title = "title2"
                )
            )
        )

        val result = sut.getTodos()

        assertEquals(result.size, 2)
        assertEquals(result[0].id, 1)
        assertEquals(result[0].title, "title1")
        assertEquals(result[1].id, 2)
        assertEquals(result[1].title, "title2")
    }
}