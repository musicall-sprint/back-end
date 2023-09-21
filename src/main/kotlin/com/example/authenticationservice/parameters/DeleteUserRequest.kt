package com.example.authenticationservice.parameters

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class DeleteUserRequest (
        @JsonProperty("password") @field:NotNull @field:NotBlank val password: String?
) {
}