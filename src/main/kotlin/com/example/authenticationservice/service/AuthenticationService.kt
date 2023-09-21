package com.example.authenticationservice.service

import com.example.authenticationservice.dao.UserRepository
import com.example.authenticationservice.security.JwtTokenProvider
import com.example.authenticationservice.dto.TypeUserDto
import com.example.authenticationservice.model.User
import com.example.authenticationservice.parameters.RegisterUserRequest
import org.mindrot.jbcrypt.BCrypt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class AuthenticationService(
        /*@Autowired @Qualifier("InMemoryDB") private val usersDao: UsersDao,*/
        @Autowired private val userRepository: UserRepository
) {
    @Autowired internal var jwtTokenProvider: JwtTokenProvider? = null

    fun registerUser(registerUserRequest: RegisterUserRequest) : String {
        if (userRepository.existsByEmailOrCpfOrTelephone(registerUserRequest.email!!, registerUserRequest.cpf!!, registerUserRequest.telephone!!)) throw ResponseStatusException(HttpStatus.CONFLICT, "User already registered", null);
        val token = UUID.randomUUID().toString();
        userRepository.save(User(registerUserRequest, token))

        return token
    }

    fun confirmUser(email: String, token: String) {
        val user = userRepository.getUserByEmail(email) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't exist")

        if (user.isConfirmed) throw ResponseStatusException(HttpStatus.CONFLICT, "User already confirmed")
        if (user.confirmationToken != token) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect confirmation token")
        user.isConfirmed = true
        user.confirmationToken = ""

        userRepository.save(user)
    }

    fun requestPasswordReset(email: String) : String {
        val user = userRepository.getUserByEmail(email) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't exist")
        if (!user.isConfirmed) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "User isn't confirmed")
        user.isPasswordResetRequested = true
        user.passwordResetToken = UUID.randomUUID().toString()

        userRepository.save(user)

        return user.passwordResetToken
    }

    fun resetPassword(email: String, password: String, token: String) {
        val user = userRepository.getUserByEmail(email) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't exist")
        if (!user.isConfirmed) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "User isn't confirmed")
        if (!user.isPasswordResetRequested) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Password change hasn't been requested")
        if (user.passwordResetToken != token) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect password change token")
        user.isPasswordResetRequested = false
        user.passwordResetToken = ""
        user.password = BCrypt.hashpw(password, BCrypt.gensalt());

        userRepository.save(user)
    }

    fun getUserWithEmail(email: String): User {
        return userRepository.getUserByEmail(email) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't exist")
    }

    fun login(email: String, password: String, type : TypeUserDto): HashMap<Any, Any> {
        val user : User = userRepository.getUserByEmailAndType(email, type) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't exist")
        if (!user.isConfirmed) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "User isn't confirmed")
        if (!BCrypt.checkpw(password, user.password)) throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username/password supplied")
        val token = jwtTokenProvider!!.createToken(user, type)
        val model = HashMap<Any, Any>()
        model["email"] = email
        model["token"] = token
        return model
    }
}