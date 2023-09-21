package com.example.authenticationservice.dto

import com.example.authenticationservice.model.EventJob

data class JobRequestEventJobDto (
    val id: Long,
    val fkEvento : Long,
    val instrumentName : String
) {
    constructor(): this(
        id = 0,
        fkEvento = 0,
        instrumentName = ""
    )
}