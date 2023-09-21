package com.example.authenticationservice.parameters

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class SetPasswordRequest(
        @JsonProperty("email") @field:NotNull @field:Email @field:NotBlank val email: String,
        @JsonProperty("password") @field:NotNull @field:NotBlank @field:Size(min = 8, max = 15) var password: String,
        @JsonProperty("token") @field:NotNull @field:NotBlank @field:Size(min = 36, max = 36) var token: String
)
