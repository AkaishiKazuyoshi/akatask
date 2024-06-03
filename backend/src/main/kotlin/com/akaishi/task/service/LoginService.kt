package com.akaishi.task.service

import com.akaishi.task.repository.LoginRepository
import org.springframework.stereotype.Service

interface LoginService {
    fun login(id: String, password: String): Boolean
}

@Service
class DefaultLoginService(
    private val loginRepository: LoginRepository
) : LoginService {
    // Encryption concept
    override fun login(id: String, password: String): Boolean {
        val optionalUser = loginRepository.findById(id)
        return optionalUser.isPresent && optionalUser.get().password == password
    }
}