package com.example.authenticationservice.dto

data class DeleteNotificationDto (
    val notificationType: NotificationTypeDto,
    val fkJobRequest: Long,
    val fkUser: Long
) {
}
