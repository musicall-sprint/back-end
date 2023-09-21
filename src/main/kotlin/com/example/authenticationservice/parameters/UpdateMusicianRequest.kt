package com.example.authenticationservice.parameters

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class UpdateMusicianRequest (
    @JsonProperty("description") val description: String?,
    @JsonProperty("cep") val cep: String?
)
