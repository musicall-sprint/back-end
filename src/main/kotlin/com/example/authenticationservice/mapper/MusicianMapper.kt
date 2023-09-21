package com.example.authenticationservice.mapper

import com.example.authenticationservice.dto.MusicianDto
import com.example.authenticationservice.model.Musician
import org.springframework.stereotype.Component

@Component
class MusicianMapper {
    fun toDto(musician: Musician) : MusicianDto {
        return MusicianDto(
                musician.description,
                musician.cep
        )
    }

}
