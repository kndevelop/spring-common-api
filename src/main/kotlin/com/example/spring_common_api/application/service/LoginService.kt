package com.example.spring_common_api.application.service

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.stereotype.Service

@Service
class LoginService {

    companion object {
        private val log = LoggerFactory.getLogger("auth")
    }

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var httpServletResponse: HttpServletResponse

    @Autowired
    lateinit var httpServletRequest: HttpServletRequest

    val repository = HttpSessionSecurityContextRepository()

    fun login(user: String, password: String) {
        val token = UsernamePasswordAuthenticationToken(user, password)
        try {
            val auth = authenticationManager.authenticate(token)
            SecurityContextHolder.getContext().authentication = auth
            repository.saveContext(
                SecurityContextHolder.getContext(),
                httpServletRequest,
                httpServletResponse
            )
        } catch (e: BadCredentialsException) {
            log.warn("password mismatch for user=$user")
            throw RuntimeException("パスワードが正しくありません")

        } catch (e: AuthenticationException) {
            log.warn("authentication failed: ${e.javaClass.simpleName}")
            throw RuntimeException("認証に失敗しました")
        }
    }
}