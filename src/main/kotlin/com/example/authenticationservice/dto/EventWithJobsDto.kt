package com.example.authenticationservice.dto

import com.example.authenticationservice.model.Event
import com.example.authenticationservice.model.EventJob
import java.time.LocalDate

data class EventWithJobsDto (
        val id: Long,
        val creatorEvent: UserDto,
        val name: String,
        val cep: String,
        val eventDate: LocalDate,
        val durationHours: Int,
        val eventJob: EventJob
) {
    constructor(): this(
            id = 0,
            creatorEvent = UserDto(),
            name = "",
            cep = "",
            eventDate = LocalDate.now(),
            durationHours = 0,
            eventJob = EventJob()
    )
}

