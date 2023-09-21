package com.example.authenticationservice.dto

import com.example.authenticationservice.model.Event
import java.time.LocalDate

data class CreateEventDto (
        val id: Long,
        val name: String,
        val cep: String,
        val eventDate: LocalDate,
        val durationHours: Int
) {
    constructor(event: Event): this(
            id = event.id,
            name = event.name,
            cep = event.cep,
            eventDate = event.eventDate,
            durationHours = event.durationHours
    )
}