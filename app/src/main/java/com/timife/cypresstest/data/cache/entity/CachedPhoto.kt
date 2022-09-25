package com.timife.cypresstest.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cached_photo")
data class CachedPhoto(
    @PrimaryKey
    val id:Int,
    val albumId:Int,
    val title:String,
    val url:String,
    val thumbnailUrl:String
    )