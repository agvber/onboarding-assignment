package com.agvber.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.agvber.core.database.model.UserEntity

@Dao
interface UserLocalDataSource {

    @Insert
    suspend fun insertUser(userEntity: UserEntity)

    @Query(
        """
            SELECT * FROM ${TableName.USER} WHERE email is :email AND password is :password
        """
    )
    suspend fun getUser(email: String, password: String): UserEntity

    @Query(
        """
            SELECT * FROM ${TableName.USER} WHERE id is :uid
        """
    )
    suspend fun getUser(uid: String): UserEntity
}