package com.akaishi.task.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity.formLogin {
            it.loginProcessingUrl("/api/login")
                .usernameParameter("id")
        }
        httpSecurity.csrf {
            it.ignoringRequestMatchers("/api/login")
        }
        return httpSecurity.build()
    }
}