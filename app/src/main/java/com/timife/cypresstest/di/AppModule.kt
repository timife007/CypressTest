package com.timife.cypresstest.di

import android.app.Application
import androidx.room.Room
import com.timife.cypresstest.data.cache.database.AlbumDatabase
import com.timife.cypresstest.data.cache.database.AlbumsDao
import com.timife.cypresstest.data.remote.AlbumsApi
import com.timife.cypresstest.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAlbumsApi(): AlbumsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BASIC
                    }).build()
            )
            .build()
            .create(AlbumsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAlbumsDatabase(app: Application): AlbumDatabase {
        return Room.databaseBuilder(
            app,
            AlbumDatabase::class.java,
            "albums.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAlbumsDao(
        albumDatabase: AlbumDatabase
    ): AlbumsDao {
        return albumDatabase.albumDao
    }
}