package com.example.authenticationservice.parameters

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class SetEmailRequest (
    @JsonProperty("token") @field:NotNull @field:NotBlank val token: String?
)