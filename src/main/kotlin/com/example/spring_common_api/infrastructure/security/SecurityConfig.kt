package com.example.spring_common_api.infrastructure.security


import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val accountUserDetailsService: AccountUserDetailsService
) {
    private val objectMapper = com.fasterxml.jackson.module.kotlin.jacksonObjectMapper()

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationManager(
        userDetailsService: AccountUserDetailsService,
        passwordEncoder: PasswordEncoder
    ): AuthenticationManager {
        val provider = DaoAuthenticationProvider(userDetailsService)
        provider.setPasswordEncoder(passwordEncoder)
        return ProviderManager(provider)
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity, authenticationManager: AuthenticationManager): SecurityFilterChain {

        http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/login",
                    "/account/register",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/error"
                ).permitAll()
                it.anyRequest().authenticated()
            }
            .logout {
                it.logoutUrl("/logout")
                it.logoutSuccessHandler { _, response, _ ->
                    response.status = HttpServletResponse.SC_OK
                }
                it.invalidateHttpSession(true)
                it.deleteCookies("JSESSIONID")
            }
            .exceptionHandling {
                it.authenticationEntryPoint { _, response, _ ->
                    response.status = HttpServletResponse.SC_UNAUTHORIZED
                }
                it.accessDeniedHandler { _, response, _ ->
                    response.status = HttpServletResponse.SC_FORBIDDEN
                }
            }
            .userDetailsService(accountUserDetailsService)

        return http.build()
    }
}
