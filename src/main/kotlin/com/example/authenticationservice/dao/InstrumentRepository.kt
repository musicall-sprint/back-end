package com.example.authenticationservice.dao

import com.example.authenticationservice.model.Instrument
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface InstrumentRepository : JpaRepository<Instrument, Long> {
}