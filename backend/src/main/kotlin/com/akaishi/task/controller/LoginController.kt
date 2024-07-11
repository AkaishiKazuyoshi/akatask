package com.akaishi.task.controller

import com.akaishi.task.model.LoginRequest
import com.akaishi.task.service.LoginService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

// I(backend server) believe only http://localhost:8080 site. because http://localhost:8080 is me!.
// * - I(backend server) believe every site. I don't care.
// I(backend server) believe http://localhost:5173 site.
//@CrossOrigin(origins = ["http://localhost:5173"]) // HTTP RESPONSE HEADER SETUP
@Controller
@RequestMapping("/api/login")
class LoginController(
    private var loginService: LoginService
) {
//    @CrossOrigin
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

// @CrossOrigin
// CorsFilter and CorsConfiguration in WebMvcConfig !!!!
