package com.agvber.core.domain.usecase

import com.agvber.core.domain.model.User
import com.agvber.core.domain.repository.UserRepository
import javax.inject.Inject

class GetUserInformationUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(uid: String): User {
        return userRepository.getUserInformation(uid)
    }

}