package com.example.authenticationservice.parameters

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class CreateEventJobRequest (
        @JsonProperty("fkEvent") @field:NotNull val fkEvent : Long?,
        @JsonProperty("fkInstrument") @field:Size(min = 1, max = 30) @field:NotNull val fkInstrument : List<Long>?
) {
}