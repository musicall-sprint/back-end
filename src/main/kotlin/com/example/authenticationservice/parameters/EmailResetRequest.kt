package com.example.authenticationservice.parameters

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class EmailResetRequest (
    @JsonProperty("email") @field:NotNull @field:NotBlank @field:Email val email: String?
) {
}