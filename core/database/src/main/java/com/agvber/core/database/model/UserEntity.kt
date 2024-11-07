package com.agvber.core.database.model

data class UserEntity(
    var id: Long = 0,
    val email: String,
    val name: String,
    val password: String
)