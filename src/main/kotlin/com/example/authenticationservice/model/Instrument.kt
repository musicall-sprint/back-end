package com.example.authenticationservice.model

import com.example.authenticationservice.dto.TypeUserDto
import com.example.authenticationservice.parameters.RegisterUserRequest
import org.mindrot.jbcrypt.BCrypt
import javax.persistence.*

@Entity
data class Instrument (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @Column(nullable = false)
        val name: String,

        @OneToMany(mappedBy = "instrument")
        val musicianInstruments: MutableList<MusicianInstrument> = mutableListOf(),

        @OneToMany(mappedBy = "instrument")
        val eventJob: MutableList<EventJob> = mutableListOf()
) {
    constructor (): this (
            name = ""
    )

}
