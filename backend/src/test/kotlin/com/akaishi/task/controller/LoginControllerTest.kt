package com.akaishi.task.controller

import com.akaishi.task.service.LoginService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@WebMvcTest(controllers = [LoginController::class])
class LoginControllerTest {
    @Autowired
    lateinit var sut: MockMvc

    @MockBean
    lateinit var stubLoginService: LoginService

    @Test
    fun when_login_fail_redirect_loginPage() {
        `when`(stubLoginService.login("user@example.com", "1234568")).thenReturn(false)


        sut.post("/api/login") {
            contentType = MediaType.APPLICATION_FORM_URLENCODED
            param("id", "user@example.com")
            param("password", "1234568")
        }.andExpect {
            redirectedUrl("http://localhost:3000")
        }
    }

    @Test
    fun when_login_success_redirect_mainPage() {
        `when`(stubLoginService.login("user@example.com", "1234567")).thenReturn(true)


        sut.post("/api/login") {
            contentType = MediaType.APPLICATION_FORM_URLENCODED
            param("id", "user@example.com")
            param("password", "1234567")
        }.andExpect {
            redirectedUrl("http://localhost:3000/main")
        }
    }
}