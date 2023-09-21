package com.example.authenticationservice.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailSenderService (
    @Autowired private val mailSender : JavaMailSender
) {
    fun sendEmail(
        toEmail : String,
        subject : String,
        body : String
    ) {
       val message : SimpleMailMessage = SimpleMailMessage()
        message.setFrom("gian.angelosp@gmail.com")
        message.setTo(toEmail)
        message.setText(body)
        message.setSubject(subject)
        mailSender.send(message)
        println("\n Email enviado")
    }

}