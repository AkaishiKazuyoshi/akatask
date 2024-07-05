package com.akaishi.task.controller

import com.akaishi.task.model.LoginRequest
import com.akaishi.task.service.LoginService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/login")
class LoginController(
    private var loginService: LoginService
) {
    @PostMapping
    fun login(
        response: HttpServletResponse,
        @RequestBody body: LoginRequest
    ) {
        if (loginService.login(body.id, body.password)) {
            response.sendRedirect("http://localhost:3000/main")
        } else {
            response.sendRedirect("http://localhost:3000")
        }
    }
}