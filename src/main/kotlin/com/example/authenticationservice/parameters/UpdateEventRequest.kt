package com.example.authenticationservice.parameters

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.validation.constraints.*

data class UpdateEventRequest (
        @JsonProperty("id") @field:NotNull val id: Long?,
        @JsonProperty("name") val name: String?,
        @JsonProperty("cep")  val cep: String?,
        @JsonProperty("complement")  val complement: String?,
        @JsonProperty("eventDate") @field:DateTimeFormat(pattern = "yyyy/MM/dd") @field:Future(message = "Event date must be in the future") val eventDate: LocalDate?,
        @JsonProperty("durationHours") @field:Positive val durationHours: Int?
) {
}