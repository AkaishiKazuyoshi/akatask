package com.akaishi.task.controller

import com.akaishi.task.config.SecurityConfig
import com.akaishi.task.entity.Todo
import com.akaishi.task.service.TodoService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(controllers = [TodoController::class])
@Import(SecurityConfig::class)
class TodoControllerTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var todoService: TodoService

    @Test
    @WithMockUser
    fun `return todos when request for api-todo`() {
        `when`(todoService.getTodos()).thenReturn(
            listOf(
                Todo(id = 1, title = "title1")
            )
        )

        mockMvc.get("/api/todos").andExpect {
                jsonPath("$[0].id") { value(1) }
                jsonPath("$[0].title") { value("title1") }
            }
        verify(todoService, times(1)).getTodos()
    }
}