package com.agvber.core.domain.usecase

import com.agvber.core.domain.model.User
import com.agvber.core.domain.repository.UserRepository
import javax.inject.Inject

class LoginAppUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ): User {
        return userRepository.getUserInformation(email, password)
    }
}