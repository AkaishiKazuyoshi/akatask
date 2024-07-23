package com.akaishi.task.service

import com.akaishi.task.repository.LoginRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.stereotype.Service


//interface LoginService {
//    fun login(id: String, password: String): Boolean
//}

@Service
class DefaultLoginService(
    @Value("\${aka-task.secret}") private val secret: String, private val loginRepository: LoginRepository
) : UserDetailsService {

//    private fun bytesToHexString(bytes: ByteArray): String {
//        val hash = StringBuilder()
//        for (aByte in bytes) {
//            val hex = "%02x".format(aByte)
//            hash.append(hex)
//        }
//        return hash.toString()
//    }
//
//    @Throws(NoSuchAlgorithmException::class, InvalidKeyException::class)
//    fun hmacWith(data: String): String {
//        val secretKeySpec = SecretKeySpec(secret.toByteArray(), "HmacSHA256")
//        val mac = Mac.getInstance("HmacSHA256") // MAC(keyed-hash message authentication code) -> encrypted code
//        mac.init(secretKeySpec)
//        return bytesToHexString(mac.doFinal(data.toByteArray()))
//    }
//
//    override fun login(id: String, password: String): Boolean {
//        val optionalUser = loginRepository.findById(id)
//        val encryptedPassword = hmacWith(password)
//        return optionalUser.isPresent && optionalUser.get().password == encryptedPassword
//    }

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

fun main() {
    // {bcrypt}$2a$10$2cPl03tysxA1oMpKW75p.OGTKiIjYv2NKasXJST0gRWsefq82Ifbm
    // {bcrypt}$2a$10$H6Lf4o.lx2yv/bnsGpG21ufA5PkkWHRs6GzC2G9WTDHnxpRMqRem6
    print(
        PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("akaishipassword")
    )
}