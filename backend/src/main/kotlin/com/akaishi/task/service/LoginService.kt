package com.akaishi.task.service

import org.springframework.stereotype.Service

interface LoginService {
    fun login(id: String, password: String): Boolean
}

@Service
class DefaultLoginService : LoginService {
    override fun login(id: String, password: String): Boolean {
        TODO("Not yet implemented")
    }
}