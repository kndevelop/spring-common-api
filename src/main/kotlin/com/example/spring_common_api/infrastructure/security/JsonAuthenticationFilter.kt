package com.example.spring_common_api.infrastructure.security

import com.example.spring_common_api.presentation.dto.LoginRequest
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JsonAuthenticationFilter(
    private val objectMapper: ObjectMapper
) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        if (request.contentType == null || !request.contentType.startsWith("application/json")) {
            throw AuthenticationServiceException("Authentication method not supported: " + request.contentType)
        }

        val loginRequest = try {
            objectMapper.readValue(request.inputStream, LoginRequest::class.java)
        } catch (e: Exception) {
            throw AuthenticationServiceException("Invalid login request", e)
        }

        val authRequest = UsernamePasswordAuthenticationToken(loginRequest.userName, loginRequest.password)
        setDetails(request, authRequest)
        return this.authenticationManager.authenticate(authRequest)
    }
}
