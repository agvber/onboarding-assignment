package com.agvber.core.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.agvber.core.database.UserLocalDataSource
import com.agvber.core.database.model.UserEntity

@Database(
    entities = [
        UserEntity::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserLocalDataSource
}