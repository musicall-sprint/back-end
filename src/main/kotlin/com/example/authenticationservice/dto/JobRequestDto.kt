package com.example.authenticationservice.dto

import com.example.authenticationservice.model.JobRequest

class
JobRequestDto (
   val id: Long,
   val musicianConfirmed: Boolean,
   val organizerConfirmed: Boolean,
   val eventJobDto: JobRequestEventJobDto,
   val eventDto: JobRequestEventDto,
   val musicianFk: Long
) {
   constructor(notificationId: Long, jobRequest: JobRequest): this(
      id = notificationId,
      musicianConfirmed = jobRequest.musicianConfirmed,
      organizerConfirmed = jobRequest.organizerConfirmed,
      eventJobDto = JobRequestEventJobDto(jobRequest.eventJob.id, jobRequest.eventJob.event.id, jobRequest.eventJob.instrument.name),
      eventDto = JobRequestEventDto(jobRequest.eventJob.event.name, jobRequest.eventJob.event.eventDate),
      musicianFk = jobRequest.musician!!.id
   )

   constructor() : this(
        id = 0,
        musicianConfirmed = false,
        organizerConfirmed = false,
        eventJobDto = JobRequestEventJobDto(),
        eventDto = JobRequestEventDto(),
        musicianFk = 0
   )
}
