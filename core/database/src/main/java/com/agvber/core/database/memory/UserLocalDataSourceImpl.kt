package com.agvber.core.database.memory

import com.agvber.core.database.UserLocalDataSource
import com.agvber.core.database.model.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UserLocalDataSourceImpl @Inject constructor() : UserLocalDataSource {

    private val users: MutableList<UserEntity> = mutableListOf()

    override suspend fun insertUser(userEntity: UserEntity) {
        val generatorID = runCatching { users.lastIndex }.getOrNull() ?: 0
        users.add(userEntity.copy(id = generatorID.toLong()))
    }
}
