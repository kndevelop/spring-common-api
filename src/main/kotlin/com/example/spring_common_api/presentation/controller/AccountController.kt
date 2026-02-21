package com.example.spring_common_api.presentation.controller

import com.example.spring_common_api.application.service.AccountService
import com.example.spring_common_api.presentation.dto.AccountRegistrationRequest
import com.example.spring_common_api.presentation.dto.AccountResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/account")
@Tag(name = "Account", description = "Account management APIs")
class AccountController(
    private val accountService: AccountService
) {

    @PostMapping("/register")
    @Operation(summary = "Register a new account")
    fun register(@RequestBody @Valid request: AccountRegistrationRequest) {
        accountService.register(request)
    }

    @GetMapping("/list")
    @Operation(summary = "Get all accounts")
    fun list(): List<AccountResponse> {
        return accountService.getAllAccounts()
    }
}
