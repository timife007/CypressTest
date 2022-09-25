package com.timife.cypresstest.presentation.states

import com.timife.cypresstest.domain.model.Photo

data class PhotoState(
    val list:List<Photo> = emptyList(),
    val isLoading: Boolean = false,
    val error:String  = ""
)