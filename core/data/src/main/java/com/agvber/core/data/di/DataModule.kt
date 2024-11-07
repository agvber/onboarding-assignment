package com.agvber.core.data.di

import com.agvber.core.data.repository.UserRepositoryImpl
import com.agvber.core.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface DataModule {

    @Binds
    fun bindUserRepo(
        impl: UserRepositoryImpl
    ): UserRepository
}