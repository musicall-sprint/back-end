package com.example.authenticationservice.controller

import com.example.authenticationservice.dto.CreateEventDto
import com.example.authenticationservice.dto.EventDto
import com.example.authenticationservice.dto.EventJobDto
import com.example.authenticationservice.dto.JobRequestDto
import com.example.authenticationservice.exceptions.ParameterException
import com.example.authenticationservice.model.EventJob
import com.example.authenticationservice.model.JobRequest
import com.example.authenticationservice.parameters.*
import com.example.authenticationservice.service.OrganizerService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import java.util.HashMap
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid


@RestController
@RequestMapping("/org")
@SecurityRequirement(name = "Bearer Authentication")
class OrganizerController (
        @Autowired private val eventService : OrganizerService
) {
    @PostMapping("/event")
    fun createEvent(req : HttpServletRequest, @Valid @RequestBody createEventRequest: CreateEventRequest) : ResponseEntity<CreateEventDto> {
        val createEventDto = eventService.createEvent(createEventRequest, req)

        return ResponseEntity.status(201).body(createEventDto)
    }

    @PostMapping("/event/job")
    fun createEventJob(req : HttpServletRequest, @Valid @RequestBody createEventJobRequest: CreateEventJobRequest) : ResponseEntity<List<EventJobDto>> {
        val eventJobsDto : List<EventJobDto> = eventService.createEventJob(createEventJobRequest, req)

        return ResponseEntity.status(201).body(eventJobsDto)
    }

    @PutMapping("/event")
    fun updateEvent(req : HttpServletRequest, @Valid @RequestBody updateEventRequest: UpdateEventRequest): ResponseEntity<EventDto> {
        val eventDto = eventService.updateEvent(updateEventRequest, req)

        return ResponseEntity.status(200).body(eventDto)
    }

    @DeleteMapping("/event")
    fun deleteEvent(req : HttpServletRequest, @Valid @RequestBody deleteEventRequest: DeleteEventRequest): ResponseEntity<Void> {
        eventService.deleteEvent(req, deleteEventRequest)

        return ResponseEntity.status(200).build()
    }

    @DeleteMapping("/event-job")
    fun deleteEventJob(req : HttpServletRequest, @Valid @RequestBody deleteEventJobRequest: DeleteEventJobRequest): ResponseEntity<Void> {
        eventService.deleteEventJob(req, deleteEventJobRequest)

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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleEmptyBodyException(ex: HttpMessageNotReadableException): Map<String, String> {
        val errors = HashMap<String, String>()
        errors["request body"] = "Request body has an error or is empty"
        return errors
    }
}