package com.example.spring_common_api.domain.repository

import com.example.spring_common_api.domain.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface AccountRepository : JpaRepository<Account, Long> {
    fun findByUserName(userName: String): Optional<Account>
    fun existsByUserName(userName: String): Boolean
}
