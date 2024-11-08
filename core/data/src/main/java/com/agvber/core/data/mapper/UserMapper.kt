package com.agvber.core.data.mapper

import com.agvber.core.database.model.UserEntity
import com.agvber.core.domain.model.User

fun UserEntity.asExternalModel() =
    User(
        uid = id.toString(),
        email = email,
        name = name,
        password = password
    )