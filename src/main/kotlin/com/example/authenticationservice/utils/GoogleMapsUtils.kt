package com.example.authenticationservice.utils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.client.RestTemplate
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
class GoogleMapsUtils(restTemplateBuilder: RestTemplateBuilder) {

    private val restTemplate: RestTemplate = restTemplateBuilder.build()

    @Value("\${google.api.key}")
    private lateinit var apiKey: String

    fun getDistanceMatrix(origins: String, destinations: String): String {
        val url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=$origins&destinations=$destinations&mode=driving&language=en-US&units=metric&key=$apiKey"

        return restTemplate.getForObject(url, String::class.java)!!
    }
}
