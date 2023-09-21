package com.example.authenticationservice.parameters

import com.example.authenticationservice.dto.TypeUserDto
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.validation.constraints.*

data class RegisterUserRequest(
        @JsonProperty("type") @field:NotNull val type: TypeUserDto?,
        @JsonProperty("name") @field:NotNull @field:NotBlank val name: String?,
        @JsonProperty("lastName") @field:NotNull @field:NotBlank val lastName: String?,
        @JsonProperty("cpf") @field:NotNull @field:NotBlank val cpf: String?,
        @JsonProperty("birthDate") @field:NotNull @field:DateTimeFormat(pattern = "yyyy/MM/dd") @field:Past val birthDate: LocalDate?,
        @JsonProperty("telephone") @field:NotNull @field:NotBlank @field:Pattern(
            regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})",
            message = "Envie um telefone válido"
        ) val telephone: String?,
        @JsonProperty("email") @field:NotNull @field:Email @field:NotBlank val email: String?,
        @JsonProperty("password") @field:NotNull @field:NotBlank @field:Size(min = 8, max = 15) var password: String?
)
