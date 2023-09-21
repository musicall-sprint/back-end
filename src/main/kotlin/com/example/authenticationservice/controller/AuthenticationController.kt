package com.example.authenticationservice.controller

import com.example.authenticationservice.exceptions.ParameterException
import com.example.authenticationservice.parameters.RegisterUserRequest
import com.example.authenticationservice.parameters.PasswordResetRequest
import com.example.authenticationservice.parameters.SetPasswordRequest
import com.example.authenticationservice.service.AuthenticationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import org.springframework.validation.FieldError
import java.util.HashMap
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.http.HttpStatus
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.example.authenticationservice.parameters.AuthenticationRequest
import com.example.authenticationservice.service.EmailSenderService
import org.apache.coyote.Response
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.server.ResponseStatusException
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@RestController
@RequestMapping("/api")
class AuthenticationController(
    @Autowired private val authenticationService: AuthenticationService,
    @Autowired private val emailSenderService : EmailSenderService
) {

    @PostMapping("/register")
    fun registerUser(@Valid @RequestBody registerUserRequest: RegisterUserRequest): ResponseEntity<Void> {
        val token = authenticationService.registerUser(registerUserRequest)
        /*emailSenderService.sendEmail(
            "${registerUserRequest.email}",
            "Email de confirmação",
            "http://localhost:8080/api/register/${registerUserRequest.email}/${token}"
        )*/

        return ResponseEntity.status(201).build()
    }

    @GetMapping("/register/{email}/{token}")
    fun confirmUser(@Valid @Email @NotBlank @PathVariable("email") email: String, @Valid @NotBlank @Size(min = 36, max = 36) @PathVariable("token") token: String): ResponseEntity<Void> {
        authenticationService.confirmUser(email, token)

        return ResponseEntity.status(200).build()
   }

    @PostMapping("/login")
    fun login(@RequestBody @Valid data: AuthenticationRequest): ResponseEntity<*> {
        val model = authenticationService.login(data.email!!, data.password!!, data.type!!)

        return ResponseEntity.ok(model)
    }


    @PostMapping("/password_reset")
    fun requestPasswordReset(@Valid @RequestBody passwordResetRequest: PasswordResetRequest): ResponseEntity<Void> {
        val resetToken = authenticationService.requestPasswordReset(passwordResetRequest.email)
        /*emailSenderService.sendEmail(
            "${passwordResetRequest.email}",
            "Código para a troca da senha",
            "${resetToken}"
        )*/

        return ResponseEntity.status(200).build()
    }

    @PutMapping("/password_reset")
    fun resetPassword(@Valid @RequestBody setPasswordRequest: SetPasswordRequest): ResponseEntity<Void> {
        authenticationService.resetPassword(setPasswordRequest.email, setPasswordRequest.password, setPasswordRequest.token);

        return ResponseEntity.status(200).build()
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): Map<String, String> {
        val errors = HashMap<String, String>()
        ex.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.getDefaultMessage()
            errors[fieldName] = errorMessage ?: "Error"
        }
        return errors
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ParameterException::class)
    fun handleValidationExceptions(ex: ParameterException): Map<String, String> {
        val errors = HashMap<String, String>()
        errors[ex.parameter] = ex.message;
        return errors
    }
}