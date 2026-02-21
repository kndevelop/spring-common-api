package com.example.spring_common_api.domain.model

import jakarta.validation.constraints.NotBlank

data class LoginModel (

    @field:NotBlank
    val loginName: String?,

    @field:NotBlank
    val password: String?
)
