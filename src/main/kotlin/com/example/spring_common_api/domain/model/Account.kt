package com.example.spring_common_api.domain.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "accounts")
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val userName: String,

    @Column(nullable = false)
    val passwordHash: String,

    @Column(nullable = false, unique = true)
    val mailAddress: String,

    @Column(nullable = true)
    var lastLoginDate: LocalDateTime? = null
)
