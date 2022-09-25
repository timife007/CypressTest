package com.timife.cypresstest.data.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.timife.cypresstest.data.cache.entity.CachedAlbum
import com.timife.cypresstest.data.cache.entity.CachedPhoto

@Dao
interface AlbumsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(
        cachedAlbum:List<CachedAlbum>
    )

    @Query("DELETE FROM cached_album")
    suspend fun clearAlbum()

    @Query("SELECT * FROM cached_album")
    suspend fun getCachedAlbums():List<CachedAlbum>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(
        cachedAlbum:List<CachedPhoto>
    )

    @Query("DELETE FROM cached_photo")
    suspend fun clearPhotos()

    @Query("SELECT * FROM cached_photo")
    suspend fun getCachedPhotos():List<CachedPhoto>

}
