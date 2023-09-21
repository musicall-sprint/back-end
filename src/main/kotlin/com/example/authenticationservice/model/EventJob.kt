package com.example.authenticationservice.model

import com.example.authenticationservice.parameters.CreateEventJobRequest
import com.example.authenticationservice.parameters.CreateEventRequest
import org.springframework.boot.autoconfigure.batch.BatchProperties.Job
import javax.persistence.*

@Entity
data class EventJob (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @OneToMany(mappedBy = "eventJob", cascade = [CascadeType.ALL], orphanRemoval = true)
        val jobRequests: MutableList<JobRequest> = mutableListOf(),

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "event_id", nullable = false)
        val event : Event,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "instrument_id", nullable = false)
        val instrument: Instrument,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "musician_id", nullable = true)
        var musician : Musician? = null
) {
    constructor(event: Event, instrument: Instrument) : this (
            event = event,
            instrument = instrument,
            musician = null
    )

    constructor() : this (
            event = Event(),
            instrument = Instrument(),
            musician = null
    )
}