package com.example.authenticationservice.dao

import com.example.authenticationservice.dto.TypeUserDto
import com.example.authenticationservice.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import javax.persistence.Id

interface UserRepository : JpaRepository<User, Long> {
    fun existsByEmailOrCpfOrTelephone(email: String, cpf: String, telephone: String): Boolean
    fun getUserByEmail(email : String) : User?
    fun getUserByEmailAndType(email : String, type : TypeUserDto) : User?
    fun getById(id : Long) : User?
}