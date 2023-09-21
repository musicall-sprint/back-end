package com.example.authenticationservice.dto

import java.time.LocalDate

class JobRequestEventDto (
    val name: String,
    val eventDate: LocalDate
) {
    constructor(): this(
        name = "",
        eventDate = LocalDate.now()
    )
}