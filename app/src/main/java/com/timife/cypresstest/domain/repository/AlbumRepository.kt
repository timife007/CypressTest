package com.timife.cypresstest.domain.repository

import com.timife.cypresstest.domain.model.Album
import com.timife.cypresstest.domain.model.Photo
import com.timife.cypresstest.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    fun getAlbums(): Flow<Resource<List<Album>>>

    fun getPhotos():Flow<Resource<List<Photo>>>
}