package com.example.authenticationservice.dao

import com.example.authenticationservice.dto.DeleteNotificationDto
import com.example.authenticationservice.dto.JobRequestDto
import com.example.authenticationservice.model.Notification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional
import javax.persistence.Tuple

interface NotificationRepository: JpaRepository<Notification, Long> {
    @Query("""
        select new com.example.authenticationservice.dto.JobRequestDto(notification.id, notification.jobRequest)
            from Notification notification
                    where notification.user.id = :userId
                        order by notification.id desc
    """)
    fun findJobRequestDtoByUserId(userId: Long): List<JobRequestDto>

    @Query("""
        select new com.example.authenticationservice.dto.DeleteNotificationDto(notification.notificationType, notification.jobRequest.id, notification.user.id)
            from Notification notification
                    where notification.user.id = :userId
                        and notification.id = :notificationId
    """)
    fun findDeleteNotificationDtoByUserIdAndNotificationId(userId: Long, notificationId: Long): DeleteNotificationDto?

    @Modifying
    @Query("delete from Notification notification where notification.jobRequest.id = :fkEventJob ")
    fun deleteByJobRequestId(fkEventJob: Long)
}
