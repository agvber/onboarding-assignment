package com.agvber.core.database.di

import com.agvber.core.database.UserLocalDataSource
import com.agvber.core.database.memory.UserLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataBaseSingletonModule {

    @Binds
    fun bindUserLocalDataSource(
        impl: UserLocalDataSourceImpl
    ): UserLocalDataSource
}