package com.akaishi.task.controller

import com.akaishi.task.config.SecurityConfig
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(controllers = [UserController::class])
@Import(SecurityConfig::class)
class UserControllerTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    @WithMockUser
    fun `return 200 status when authorized user`() {
        mockMvc.get("/api/auth/me").andExpect {
                status { isOk() }
            }
    }

    @Test
    fun `return 403 status when not authorized user`() {
        mockMvc.get("/api/auth/me").andExpect {
                status { isForbidden() }
            }
    }
}