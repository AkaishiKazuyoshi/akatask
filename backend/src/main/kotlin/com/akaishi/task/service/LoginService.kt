package com.akaishi.task.service

import com.akaishi.task.repository.LoginRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class DefaultLoginService(
    private val loginRepository: LoginRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val optionalUser = loginRepository.findById(username!!)
        val userEntity = optionalUser.orElseThrow {
            EntityNotFoundException("$username is not existed")
        }
        return User(
            username, userEntity.password, listOf(SimpleGrantedAuthority("ROLE_USER"))
        )
    }
}
