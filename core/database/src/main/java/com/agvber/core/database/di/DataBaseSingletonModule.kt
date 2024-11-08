package com.agvber.core.database.di

import android.content.Context
import androidx.room.Room
import com.agvber.core.database.UserLocalDataSource
import com.agvber.core.database.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DB_NAME = "database-name"

@Module
@InstallIn(SingletonComponent::class)
internal object DataBaseSingletonModule {

    @Singleton
    @Provides
    fun provideDB(
        @ApplicationContext appContext: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context = appContext,
            klass = AppDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase): UserLocalDataSource = db.userDao()
}