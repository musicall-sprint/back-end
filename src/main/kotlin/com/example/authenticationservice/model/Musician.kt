package com.example.authenticationservice.model

import com.example.authenticationservice.parameters.RegisterMusicianRequest
import javax.persistence.*

@Entity
data class Musician(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @OneToMany(mappedBy = "musician", cascade = [CascadeType.ALL], orphanRemoval = true)
    val jobRequests: MutableList<JobRequest> = mutableListOf(),

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id", nullable = false)
    val user: User,

    @Column(nullable = false)
    var description: String,

    @Column(nullable = false)
    var cep: String,

    @OneToMany(mappedBy = "musician", cascade = [CascadeType.ALL], orphanRemoval = true)
    val musicianInstruments: MutableList<MusicianInstrument> = mutableListOf(),

    @OneToMany(mappedBy = "musician", cascade = [CascadeType.ALL], orphanRemoval = true)
    val eventJob: MutableList<EventJob> = mutableListOf()
){
    constructor() : this(
        user = User(),
        description = "",
        cep = ""
    )

    constructor(registerMusicianRequest: RegisterMusicianRequest, user: User) : this(
        user = user,
        description = registerMusicianRequest.description!!,
        cep = registerMusicianRequest.cep!!
    )
}