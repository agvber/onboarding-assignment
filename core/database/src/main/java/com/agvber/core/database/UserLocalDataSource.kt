package com.agvber.core.database

import com.agvber.core.database.model.UserEntity

interface UserLocalDataSource {

    suspend fun insertUser(userEntity: UserEntity)
}