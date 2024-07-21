package com.akaishi.task.service

import com.akaishi.task.repository.LoginRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


interface LoginService {
    fun login(id: String, password: String): Boolean
}

@Service
class DefaultLoginService(
    @Value("\${aka-task.secret}")
    private val secret: String,
    private val loginRepository: LoginRepository
) : LoginService, UserDetailsService {

    private fun bytesToHexString(bytes: ByteArray): String {
        val hash = StringBuilder()
        for (aByte in bytes) {
            val hex = "%02x".format(aByte)
            hash.append(hex)
        }
        return hash.toString()
    }

    @Throws(NoSuchAlgorithmException::class, InvalidKeyException::class)
    fun hmacWith(data: String): String {
        val secretKeySpec = SecretKeySpec(secret.toByteArray(), "HmacSHA256")
        val mac = Mac.getInstance("HmacSHA256") // MAC(keyed-hash message authentication code) -> encrypted code
        mac.init(secretKeySpec)
        return bytesToHexString(mac.doFinal(data.toByteArray()))
    }

    // Encryption concept
    override fun login(id: String, password: String): Boolean {
        val optionalUser = loginRepository.findById(id)
        val encryptedPassword = hmacWith(password)
        return optionalUser.isPresent && optionalUser.get().password == encryptedPassword
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        TODO("Not yet implemented")
    }
}