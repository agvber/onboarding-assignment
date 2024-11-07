package com.agvber.core.data.repository

import com.agvber.core.database.UserLocalDataSource
import com.agvber.core.database.model.UserEntity
import com.agvber.core.domain.repository.UserRepository
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) : UserRepository {

    override suspend fun storeUserInformation(
        email: String,
        name: String,
        password: String
    ) {
        val entity = UserEntity(email = email, name = name, password = password)
        userLocalDataSource.insertUser(entity)
    }
}