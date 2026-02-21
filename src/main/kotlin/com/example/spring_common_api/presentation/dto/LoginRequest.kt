package com.example.spring_common_api.presentation.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginRequest(
    @JsonProperty("user_name")
    val userName: String,
    val password: String
)
