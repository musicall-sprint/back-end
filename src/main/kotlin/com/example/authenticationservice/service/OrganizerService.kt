package com.example.authenticationservice.service

import com.example.authenticationservice.dao.*
import com.example.authenticationservice.dto.CreateEventDto
import com.example.authenticationservice.dto.EventDto
import com.example.authenticationservice.dto.EventJobDto
import com.example.authenticationservice.dto.JobRequestDto
import com.example.authenticationservice.model.Event
import com.example.authenticationservice.model.EventJob
import com.example.authenticationservice.model.Instrument
import com.example.authenticationservice.parameters.*
import com.example.authenticationservice.security.JwtTokenProvider
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate
import javax.servlet.http.HttpServletRequest
import javax.validation.constraints.Future
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@Service
class OrganizerService (
    @Autowired private val eventRepository: EventRepository,
    @Autowired private val jwtTokenProvider: JwtTokenProvider,
    @Autowired private val userRepository: UserRepository,
    @Autowired private val instrumentRepository: InstrumentRepository,
    @Autowired private val eventJobRepository : EventJobRepository
) {
    fun createEvent(createEventRequest: CreateEventRequest, req : HttpServletRequest) : CreateEventDto {
        val token  = jwtTokenProvider.resolveToken(req) ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "User invalid role JWT token.")
        val id = jwtTokenProvider.getId(token).toLong()
        val user = userRepository.getById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        if (eventRepository.existsByEventDateAndFinalized(createEventRequest.eventDate!!, false)) throw ResponseStatusException(HttpStatus.CONFLICT, "You have already an event at this date")

        val event = Event(createEventRequest, user)

        eventRepository.save(event)

        return CreateEventDto(event)
    }

    fun createEventJob(createEventJobRequest: CreateEventJobRequest, req: HttpServletRequest): List<EventJobDto> {
        val token  = jwtTokenProvider.resolveToken(req) ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "User invalid role JWT token.")
        val id = jwtTokenProvider.getId(token).toLong()
        val user = userRepository.getById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        val event = eventRepository.findByIdAndUserAndFinalized(createEventJobRequest.fkEvent!!, user, false) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "You cant create jobs for this event")

        val instruments = instrumentRepository.findAll()
        val instrumentMap : HashMap<Long, Instrument> = HashMap()
        instruments.forEach { instrumentMap[it.id] = it }

        val eventJobs = createEventJobRequest.fkInstrument!!.map {
            if (instrumentMap[it] != null) EventJob(event, instrumentMap[it]!!)
            else throw ResponseStatusException(HttpStatus.NOT_FOUND, "Instrument not found")
        }

       eventJobRepository.saveAll(eventJobs)

        return eventJobs.map{ EventJobDto(it) }
    }

    fun deleteEvent(req: HttpServletRequest, deleteEventRequest: DeleteEventRequest) {
        val token  = jwtTokenProvider.resolveToken(req) ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "User invalid role JWT token.")
        val id = jwtTokenProvider.getId(token).toLong()
        val user = userRepository.getById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        val event = eventRepository.findByIdAndUserAndFinalized(deleteEventRequest.id!!, user, false) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Can't find this event")

        eventRepository.delete(event)
    }

    fun updateEvent(updateEventRequest: UpdateEventRequest, req: HttpServletRequest): EventDto {
        val token  = jwtTokenProvider.resolveToken(req) ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "User invalid role JWT token.")
        val id = jwtTokenProvider.getId(token).toLong()
        val user = userRepository.getById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        val event = eventRepository.findByIdAndUserAndFinalized(updateEventRequest.id!!, user, false) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Can't find this event")

        var hasChanges = false

        if (updateEventRequest.name != null) {
            event.name = if (updateEventRequest.name == event.name) throw ResponseStatusException(HttpStatus.CONFLICT, "The name is the same") else updateEventRequest.name
            hasChanges = true
        }

        if (updateEventRequest.cep != null) {
            event.cep = if (updateEventRequest.cep == event.cep) throw ResponseStatusException(HttpStatus.CONFLICT, "The cep is the same") else updateEventRequest.cep
            hasChanges = true
        }

        if (updateEventRequest.complement != null) {
            event.complement = if (updateEventRequest.complement == event.complement) throw ResponseStatusException(HttpStatus.CONFLICT, "The local is the same") else updateEventRequest.complement
            hasChanges = true
        }

        if (updateEventRequest.eventDate != null){
            event.eventDate = if (updateEventRequest.eventDate == event.eventDate) throw ResponseStatusException(HttpStatus.CONFLICT, "The event date is the same") else updateEventRequest.eventDate
            hasChanges = true
        }

        if (updateEventRequest.durationHours != null) {
            event.durationHours = if (updateEventRequest.durationHours == event.durationHours) throw ResponseStatusException(HttpStatus.CONFLICT, "The duration in hours is the same") else updateEventRequest.durationHours
            hasChanges = true
        }

        if (!hasChanges) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "You don't have any change")

        eventRepository.save(event)

        return EventDto(event)
    }

    fun deleteEventJob(req: HttpServletRequest, deleteEventJobRequest: DeleteEventJobRequest) {
        val token  = jwtTokenProvider.resolveToken(req) ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "User invalid role JWT token.")
        val id = jwtTokenProvider.getId(token).toLong()
        val user = userRepository.getById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        val event = eventRepository.findByIdAndUserAndFinalized(deleteEventJobRequest.fkEvent!!, user, false) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Can't find this event")
        if (!eventJobRepository.existsByIdAndEvent(deleteEventJobRequest.fkEvent!!, event)) throw  ResponseStatusException(HttpStatus.NOT_FOUND, "You cannot delete this event job")

        eventJobRepository.deleteById(deleteEventJobRequest.id!!)
    }
}