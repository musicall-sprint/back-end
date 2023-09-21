package com.example.authenticationservice.parameters

import com.example.authenticationservice.dto.TypeUserDto
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.validation.constraints.*

data class RegisterMusicianRequest (
    @JsonProperty("description") @field:NotNull @field:NotBlank val description: String?,
    @JsonProperty("cep") @field:NotNull @field:NotBlank val cep: String?
)