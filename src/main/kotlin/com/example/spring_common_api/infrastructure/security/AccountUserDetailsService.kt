package com.example.spring_common_api.infrastructure.security

import com.example.spring_common_api.domain.repository.AccountRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AccountUserDetailsService(
    private val accountRepository: AccountRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val account = accountRepository.findByUserName(username)
            .orElseThrow { UsernameNotFoundException("User not found: $username") }
        return AccountUserDetails(account)
    }
}
