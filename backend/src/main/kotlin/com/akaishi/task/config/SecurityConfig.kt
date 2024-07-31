package com.akaishi.task.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity.formLogin {
            it.loginProcessingUrl("/api/login")
                .usernameParameter("id")
                .defaultSuccessUrl("http://localhost:5173/main")
        }
        httpSecurity.csrf {
            it.ignoringRequestMatchers("/api/login")
        }
        httpSecurity.exceptionHandling {
            configurer -> configurer
                .authenticationEntryPoint(Http403ForbiddenEntryPoint())
        }
        httpSecurity.authorizeHttpRequests {
            configurer -> configurer
                .anyRequest().authenticated()
        }
        return httpSecurity.build()
    }
}