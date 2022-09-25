package com.timife.cypresstest.data.remote.dtos


import com.squareup.moshi.Json

data class AlbumsDtoItem(
    @Json(name = "userId")
    val userId: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String
)