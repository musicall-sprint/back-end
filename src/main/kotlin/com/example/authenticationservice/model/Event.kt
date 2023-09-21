package com.example.authenticationservice.model

import com.example.authenticationservice.parameters.CreateEventRequest
import java.time.LocalDate
import javax.persistence.*

@Entity
data class Event
  (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn (name = "user_id", nullable = false)
        val user: User,

        @Column(nullable = false)
        var name: String,

        @Column(nullable = false)
        var cep: String,

        @Column(nullable = false)
        var complement: String,

        @Column(nullable = false)
        var eventDate: LocalDate,

        @Column(nullable = false)
        var durationHours : Int,

        @OneToMany(mappedBy = "event", cascade = [CascadeType.ALL], orphanRemoval = true)
        val eventJob: MutableList<EventJob> = mutableListOf()
    ) {
        @Column(nullable = false)
        var finalized = false

        constructor(
            createEventRequest: CreateEventRequest, creator: User
        ) : this(
                user = creator,
                name =  createEventRequest.name!!,
                cep =  createEventRequest.cep!!,
                complement = createEventRequest.complement!!,
                eventDate =  createEventRequest.eventDate!!,
                durationHours =  createEventRequest.durationHours!!
        )

        constructor() :this (
                user = User(),
                name =  "",
                cep =  "",
                complement = "",
                eventDate =  LocalDate.now(),
                durationHours =  0
        )
    }
