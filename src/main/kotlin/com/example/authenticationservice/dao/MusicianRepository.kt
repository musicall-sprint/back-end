package com.example.authenticationservice.dao

import com.example.authenticationservice.dto.TypeUserDto
import com.example.authenticationservice.model.Musician
import com.example.authenticationservice.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import javax.transaction.Transactional


interface MusicianRepository : JpaRepository<Musician, Long> {
    fun existsByUser(user: User) : Boolean
    fun getByUser(user: User): Musician?

    @Query("SELECT m.cep FROM Musician m WHERE m.user.id = :userId")
    fun findCepByUserId(userId: Long): String?
    @Query("SELECT m FROM Musician m WHERE m.user.id = :id")
    fun getMusicianByUserId(id: Long): Musician?
    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END\n FROM Musician m WHERE m.user.id = :userId")
    fun existsByUserId(userId: Long): Boolean

    @Query("SELECT mi.instrument.id FROM Musician m JOIN m.musicianInstruments mi WHERE m.user.id = :userId")
    fun findInstrumentIdsByUserId(userId: Long): List<Long>
    fun findByUserId(id: Long): Musician?

    @Query("SELECT m.id FROM Musician m WHERE m.user.id = :fkUser")
    fun findIdByUserId(fkUser: Long): Long?
}
