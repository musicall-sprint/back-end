package com.example.authenticationservice.model

import com.example.authenticationservice.dto.TypeUserDto
import javax.persistence.*

@Entity
data class Prospect (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val telefone: String,

    @Column(nullable = false)
    val midia: String
)