package com.timife.cypresstest.data.repositories

import com.timife.cypresstest.data.cache.database.AlbumsDao
import com.timife.cypresstest.data.mappers.toAlbum
import com.timife.cypresstest.data.mappers.toCachedAlbum
import com.timife.cypresstest.data.mappers.toCachedPhoto
import com.timife.cypresstest.data.mappers.toPhoto
import com.timife.cypresstest.data.remote.AlbumsApi
import com.timife.cypresstest.domain.model.Album
import com.timife.cypresstest.domain.model.Photo
import com.timife.cypresstest.domain.repository.AlbumRepository
import com.timife.cypresstest.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val dao:AlbumsDao,
    private val api:AlbumsApi
):AlbumRepository {
    override fun getAlbums(): Flow<Resource<List<Album>>> {
        return flow {
            emit(Resource.Loading(true))
            val cachedAlbums = dao.getCachedAlbums()
            emit(Resource.Success(data = cachedAlbums.map {
                    it.toAlbum()
                }))

            val isDbEmpty = cachedAlbums.isEmpty()
            val shouldLoadFromDb = !isDbEmpty
            if (shouldLoadFromDb) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteAlbums = try {
                api.getAlbums()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(
                    Resource.Error("Could not reach server. Please check internet connection"
                    )
                )
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error( "Unable to fetch data. An unexpected error occurred."))
                null
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Oops! An unexpected error occurred"))
                null
            }

            val remotePhotos = try {
                api.getPhotos()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(
                    Resource.Error("Could not reach server. Please check internet connection"
                    )
                )
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error( "Unable to fetch data. An unexpected error occurred."))
                null
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Oops! An unexpected error occurred"))
                null
            }

            remoteAlbums?.let{ albums ->
                dao.clearAlbum()
                dao.insertAlbum(
                    albums.map {
                        it.toAlbum().toCachedAlbum()
                    }
                )
                dao.clearPhotos()
                dao.insertPhotos(
                    remotePhotos!!.map {
                        it.toPhoto().toCachedPhoto()
                    }
                )
                emit(Resource.Success(
                    dao.getCachedAlbums().map {
                        it.toAlbum()
                    }
                ))
                emit(Resource.Loading(
                    false
                )
                )
            }

        }
    }

    override fun getPhotos(): Flow<Resource<List<Photo>>> {
        return flow {
            val cachedPhotos = dao.getCachedPhotos()
            emit(Resource.Success(data = cachedPhotos.map {
                it.toPhoto()
            }))
        }
    }
}