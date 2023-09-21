package com.example.authenticationservice.dto

class DeleteJobRequestDto (
    val id: Long,
    val organizerConfirmed: Boolean
) {
    constructor(): this(
        id = 0,
        organizerConfirmed = false
    )
}