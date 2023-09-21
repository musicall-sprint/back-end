package com.example.authenticationservice.parameters

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class ConfirmUserRequest(
        @JsonProperty("email") @field:NotNull @field:Email @field:NotBlank val email: String?,
        @JsonProperty("token") @field:NotNull @field:NotBlank @field:Size(min = 36, max = 36) var token: String?
)