package com.example.authenticationservice.utils

object UrlUtils {
    fun getEndpoint(url: String): String {
        val segments = url.split("/")
        return if (segments.size >= 4) {
            segments[3]
        } else {
            ""
        }
    }
}