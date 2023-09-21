package com.example.authenticationservice.model

import javax.persistence.*

@Entity
class JobRequest (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "event_job_id", nullable = false)
    val eventJob: EventJob,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "musician_id", nullable = false)
    var musician: Musician? = null,

    @OneToMany(mappedBy = "user")
    val notifications: MutableList<Notification> = mutableListOf(),

    @Column(nullable = false)
    var organizerConfirmed: Boolean = false,

    @Column(nullable = false)
    var musicianConfirmed: Boolean = false
 ) {
    constructor(): this (
        eventJob = EventJob(),
        musician = Musician()
    )
}
