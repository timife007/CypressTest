package com.timife.cypresstest.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.timife.cypresstest.data.cache.entity.CachedAlbum
import com.timife.cypresstest.data.cache.entity.CachedPhoto

@Database(
    entities = [CachedAlbum::class, CachedPhoto::class],
    version = 1,
    exportSchema = false
)
abstract class AlbumDatabase:RoomDatabase(){
    abstract val albumDao : AlbumsDao
}