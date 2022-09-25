package com.timife.cypresstest.data.mappers

import com.timife.cypresstest.data.cache.entity.CachedPhoto
import com.timife.cypresstest.data.remote.dtos.PhotosDtoItem
import com.timife.cypresstest.domain.model.Photo

fun PhotosDtoItem.toPhoto(): Photo{
    return Photo(
        id = id,
        albumId = albumId,
        title = title,
        url = url,
        thumbnailUrl = thumbnailUrl
    )
}

fun Photo.toCachedPhoto(): CachedPhoto {
    return CachedPhoto(
        id = id,
        albumId = albumId,
        title = title,
        url = url,
        thumbnailUrl = thumbnailUrl
    )
}

fun CachedPhoto.toPhoto(): Photo {
    return Photo(
        id = id,
        albumId = albumId,
        title = title,
        url = url,
        thumbnailUrl = thumbnailUrl
    )
}