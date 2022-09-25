package com.timife.cypresstest.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cached_album")
data class CachedAlbum(
    @PrimaryKey
    val id:Int,
    val userId:Int,
    val title:String
)
