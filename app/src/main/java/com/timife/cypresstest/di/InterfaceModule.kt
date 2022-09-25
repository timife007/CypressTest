package com.timife.cypresstest.di

import com.timife.cypresstest.data.repositories.AlbumRepositoryImpl
import com.timife.cypresstest.domain.repository.AlbumRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InterfaceModule {

    @Singleton
    @Binds
    abstract fun getRepository(albumRepositoryImpl: AlbumRepositoryImpl):AlbumRepository
}