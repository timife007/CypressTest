package com.timife.cypresstest.data.mappers

import com.timife.cypresstest.data.cache.entity.CachedAlbum
import com.timife.cypresstest.data.remote.dtos.AlbumsDtoItem
import com.timife.cypresstest.domain.model.Album


fun AlbumsDtoItem.toAlbum():Album{
    return Album(
        id = id,
        userId = userId,
        title = title
    )
}

fun Album.toCachedAlbum():CachedAlbum{
    return CachedAlbum(
        id = id,
        userId = userId,
        title = title
    )
}


fun CachedAlbum.toAlbum():Album{
    return Album(
        id = id,
        userId = userId,
        title = title
    )
}