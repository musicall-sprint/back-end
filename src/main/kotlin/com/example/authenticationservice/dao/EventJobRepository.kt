package com.example.authenticationservice.dao

import com.example.authenticationservice.model.Event
import com.example.authenticationservice.model.EventJob
import com.example.authenticationservice.model.Musician
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

interface EventJobRepository : JpaRepository<EventJob, Long> {
    abstract fun existsByIdAndEvent(id: Long, event: Event): Boolean
    @Query("SELECT ej.event FROM EventJob ej WHERE ej.id = :fkEventJob")
    fun findEventById(@Param("fkEventJob") fkEventJob: Long): Event?
    fun getById(fkEventJob: Long): EventJob?
    @Query("SELECT COUNT(e) > 0 FROM EventJob e WHERE e.event.eventDate = :eventDate AND e.musician.id = :musicianId")
    fun existsByEventDateAndMusicianId(eventDate: LocalDate, musicianId: Long): Boolean
}
