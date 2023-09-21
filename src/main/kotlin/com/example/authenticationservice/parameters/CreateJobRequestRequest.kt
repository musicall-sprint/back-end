package com.example.authenticationservice.parameters

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class CreateJobRequestRequest (
    @JsonProperty("fkEventJob") @NotNull val fkEventJob: Long?
)
