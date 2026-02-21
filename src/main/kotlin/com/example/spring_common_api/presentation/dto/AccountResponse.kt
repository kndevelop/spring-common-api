package com.example.spring_common_api.presentation.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class AccountResponse(
    @field:JsonProperty("user_name")
    val userName: String,

    @field:JsonProperty("mailaddress")
    val mailAddress: String,

    val lastLoginDate: LocalDateTime?
)
