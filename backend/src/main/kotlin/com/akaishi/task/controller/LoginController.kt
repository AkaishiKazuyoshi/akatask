package com.akaishi.task.controller

import com.akaishi.task.model.LoginRequest
import com.akaishi.task.service.DefaultLoginService
import com.akaishi.task.service.LoginService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/login")
class LoginController {
    @Autowired
    var loginService: LoginService = DefaultLoginService()

    @PostMapping
    fun login(
        response: HttpServletResponse,
        body: LoginRequest
    ) {
        if (loginService.login(body.id, body.password)) {
            response.sendRedirect("http://localhost:3000/main")
        } else {
            response.sendRedirect("http://localhost:3000")
        }
    }
}