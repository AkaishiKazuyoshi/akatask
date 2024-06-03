package com.akaishi.task.service

import com.akaishi.task.entity.UserEntity
import com.akaishi.task.repository.LoginRepository
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class LoginServiceTest {
    @Autowired
    lateinit var loginRepository: LoginRepository

    lateinit var sut: LoginService

    @Test
    fun `given collect id and password when login was called return true`() {
        loginRepository.save(UserEntity(id = "1234", password = "abcd"))


        sut = DefaultLoginService(loginRepository = loginRepository)
        val result = sut.login(id = "1234", password = "abcd")


        assertTrue(result)
    }

    @Test
    fun `given user is not existed when login was called return false`() {
        sut = DefaultLoginService(loginRepository = loginRepository)
        val result = sut.login(id = "1234", password = "abcd")


        assertFalse(result)
    }


    @Test
    fun `given invalid password existed when login was called return false`() {
        loginRepository.save(UserEntity(id = "1234", password = "abcd"))


        sut = DefaultLoginService(loginRepository = loginRepository)
        val result = sut.login(id = "1234", password = "efgh")


        assertFalse(result)
    }
}