package com.example.authenticationservice.parameters

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class PasswordResetRequest(@JsonProperty("email") @field:NotNull @field:Email @field:NotBlank val email: String)