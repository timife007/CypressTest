package com.timife.cypresstest.presentation.states

import com.timife.cypresstest.domain.model.Album

data class AlbumState(
    val list:List<Album> = emptyList(),
    val isLoading: Boolean = false,
    val error:String  = ""
)
