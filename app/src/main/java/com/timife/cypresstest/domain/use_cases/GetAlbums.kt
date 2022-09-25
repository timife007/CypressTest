package com.timife.cypresstest.domain.use_cases

import com.timife.cypresstest.domain.model.Album
import com.timife.cypresstest.domain.repository.AlbumRepository
import com.timife.cypresstest.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbums @Inject constructor(
    private val repository : AlbumRepository
) {

    operator fun invoke(): Flow<Resource<List<Album>>> {
        return repository.getAlbums()
    }
}