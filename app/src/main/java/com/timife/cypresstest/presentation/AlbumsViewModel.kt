package com.timife.cypresstest.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.cypresstest.domain.use_cases.GetAlbums
import com.timife.cypresstest.domain.use_cases.GetPhotos
import com.timife.cypresstest.presentation.states.AlbumState
import com.timife.cypresstest.presentation.states.PhotoState
import com.timife.cypresstest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val getAlbums: GetAlbums,
    private val getPhotos: GetPhotos
):ViewModel(){

    var albumState by mutableStateOf(AlbumState())

    private val _photoState = mutableStateOf(PhotoState())
    var photoState : State<PhotoState> = _photoState


    init {
        viewModelScope.launch {
            val albumResult = async { getAlbums()}
            val photoResult = async { getPhotos()}

            albumResult.await().collect{resource ->
                albumState = when (resource) {
                    is Resource.Success -> {
                        albumState.copy(list = resource.data ?: emptyList())
                    }
                    is Resource.Loading -> {
                        albumState.copy(isLoading = resource.isLoading)
                    }
                    is Resource.Error -> {
                        albumState.copy(
                            error = resource.message ?: "An unexpected error occurred"
                        )
                    }
                }
            }

            photoResult.await().collect{resource ->
                when (resource) {
                    is Resource.Success -> {
                        _photoState.value = PhotoState(list = resource.data ?: emptyList())
                    }
                    is Resource.Loading -> {
                        _photoState.value = PhotoState(isLoading = resource.isLoading)
                    }
                    is Resource.Error -> {
                        _photoState.value = PhotoState(error = resource.message ?: "An unexpected error occurred")
                    }
                }
            }
        }
    }
}