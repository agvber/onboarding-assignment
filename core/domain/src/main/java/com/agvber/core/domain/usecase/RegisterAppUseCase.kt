package com.agvber.core.domain.usecase

import com.agvber.core.domain.entity.SignupEntity
import com.agvber.core.domain.repository.UserRepository
import javax.inject.Inject

class RegisterAppUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(
        email: String,
        name: String,
        password: String
    ) {
        with(SignupEntity(email, name, password)) {
            checkEmailFormat()
            checkNameFormat()
            checkPasswordFormat()
        }

        userRepository.storeUserInformation(
            email = email,
            name = name,
            password = password
        )
    }
}