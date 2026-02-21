package com.example.spring_common_api.presentation.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class AccountRegistrationRequest(
    @field:NotBlank
    @field:Size(min = 4, max = 20)
    @field:JsonProperty("user_name")
    val userName: String,

    @field:NotBlank
    @field:Size(min = 8)
    val password: String,

    @field:NotBlank
    @field:JsonProperty("password_confirm")
    val passwordConfirm: String,

    @field:NotBlank
    @field:Email
    @field:JsonProperty("mailaddress")
    val mailAddress: String
)
