package com.example.authenticationservice.dao

import com.example.authenticationservice.dto.DeleteJobRequestDto
import com.example.authenticationservice.model.JobRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface JobRequestRepository: JpaRepository<JobRequest, Long> {
    @Query("SELECT COUNT(j) > 0 FROM JobRequest j WHERE j.musician.id = :musicianId AND j.eventJob.event.id = :eventId")
    fun existsByMusicianIdAndEventId(musicianId: Long, eventId: Long): Boolean

    @Query("""
        SELECT new com.example.authenticationservice.dto.DeleteJobRequestDto(j.id, j.organizerConfirmed)
            FROM JobRequest j 
                WHERE j.eventJob.id = :fkEventJob 
                    AND j.musician.id = :musicianId
     """
    )
    fun findIdAndOrganizerConfirmedByEventJobIdAndMusicianId(fkEventJob: Long, musicianId: Long): DeleteJobRequestDto?
}
