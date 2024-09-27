package com.akaishi.task.service

import com.akaishi.task.entity.UserEntity
import com.akaishi.task.repository.LoginRepository
import jakarta.persistence.EntityNotFoundException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.security.core.authority.SimpleGrantedAuthority

@DataJpaTest
class LoginServiceTests {
    @Autowired
    lateinit var loginRepository: LoginRepository

    lateinit var sut: DefaultLoginService

    @BeforeEach
    fun setUp() {
        sut = DefaultLoginService(loginRepository = loginRepository)
    }

    @Test
    fun `given user is existed when loadUserByUsername then return user`() {
        val userName = "Akaishi"
        val password = "password"
        loginRepository.save(UserEntity(id = userName, password = password))


        val result = sut.loadUserByUsername(userName)


        assertEquals(result.username, userName)
        assertEquals(result.password, password)
        assertIterableEquals(result.authorities, listOf(SimpleGrantedAuthority("ROLE_USER")))
    }

    @Test
    fun `given user is not existed when loadUserByUsername then throw EntityNotFoundException`() {
        val userName = "Akaishi"


        val throwable = assertThrows(EntityNotFoundException::class.java) {
            sut.loadUserByUsername(userName)
        }
        assertEquals("Akaishi is not existed", throwable.message)
    }
}