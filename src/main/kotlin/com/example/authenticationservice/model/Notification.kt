package com.example.authenticationservice.model

import com.example.authenticationservice.dto.NotificationTypeDto
import javax.persistence.*

@Entity
data class Notification (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user : User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_request_id", nullable = false)
    val jobRequest: JobRequest,

    @Column(nullable = false)
    val notificationType: NotificationTypeDto? = null
) {
    constructor() : this(
        user = User(),
        jobRequest = JobRequest()
    )
}
