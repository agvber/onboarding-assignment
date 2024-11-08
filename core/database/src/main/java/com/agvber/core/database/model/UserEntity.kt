package com.agvber.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.agvber.core.database.TableName

@Entity(TableName.USER)
data class UserEntity(
    @PrimaryKey var id: Long = 0,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "password") val password: String
)