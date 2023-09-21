package com.example.authenticationservice.parameters

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.validation.constraints.Future
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class DeleteEventJobRequest (
    @JsonProperty("id") @field:NotNull val id: Long?,
    @JsonProperty("fkEvent") @field:NotNull val fkEvent: Long?
) {
}

