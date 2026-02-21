package com.example.spring_common_api.application.service

import com.example.spring_common_api.domain.model.Account
import com.example.spring_common_api.domain.repository.AccountRepository
import com.example.spring_common_api.presentation.dto.AccountRegistrationRequest
import com.example.spring_common_api.presentation.dto.AccountResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun register(request: AccountRegistrationRequest) {
        if (accountRepository.existsByUserName(request.userName)) {
            throw IllegalArgumentException("Username already exists")
        }
        if (request.password != request.passwordConfirm) {
            throw IllegalArgumentException("Passwords do not match")
        }

        val account = Account(
            userName = request.userName,
            passwordHash = passwordEncoder.encode(request.password!!)!!,
            mailAddress = request.mailAddress
        )
        accountRepository.save(account)
    }

    @Transactional(readOnly = true)
    fun getAllAccounts(): List<AccountResponse> {
        return accountRepository.findAll().map {
            AccountResponse(
                userName = it.userName,
                mailAddress = it.mailAddress,
                lastLoginDate = it.lastLoginDate
            )
        }
    }
}
