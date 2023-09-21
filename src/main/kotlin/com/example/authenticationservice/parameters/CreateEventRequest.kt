package com.example.authenticationservice.parameters

import com.example.authenticationservice.model.User
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.validation.Valid
import javax.validation.constraints.*

data class CreateEventRequest (
        @JsonProperty("name") @field:NotNull @field:NotBlank val name: String?,
        @JsonProperty("cep") @field:Pattern(regexp = "^\\d{5}-\\d{3}$", message = "Invalid CEP format") @field:NotNull @field:NotBlank val cep: String?,
        @JsonProperty("complement")  @field:NotNull @field:NotBlank val complement: String?,
        @JsonProperty("eventDate") @field:NotNull @field:DateTimeFormat(pattern = "yyyy/MM/dd") @field:Future(message = "Event date must be in the future") val eventDate: LocalDate?,
        @JsonProperty("durationHours") @field:NotNull @field:Positive val durationHours: Int?
) {
}