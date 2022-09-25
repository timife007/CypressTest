package com.timife.cypresstest.data.remote

import com.timife.cypresstest.data.remote.dtos.AlbumsDtoItem
import com.timife.cypresstest.data.remote.dtos.PhotosDtoItem
import retrofit2.http.GET

interface AlbumsApi {
    @GET("/albums")
    suspend fun getAlbums(): List<AlbumsDtoItem>

    @GET("/photos")
    suspend fun getPhotos(): List<PhotosDtoItem>
}

