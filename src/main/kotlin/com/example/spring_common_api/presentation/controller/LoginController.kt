package com.example.spring_common_api.presentation.controller

import com.example.spring_common_api.application.service.LoginService
import com.example.spring_common_api.domain.model.LoginModel
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Login", description = "Authenticate APIs")
class LoginController() {
    @Autowired
    lateinit var service: LoginService

    @PostMapping("/login")
    fun login(request: HttpServletRequest, @RequestBody @Validated model: LoginModel) {
        service.login(model.loginName!!, model.password!!)
    }
}