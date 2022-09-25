package com.timife.cypresstest.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.timife.cypresstest.presentation.AlbumsViewModel

@Composable
fun AlbumScreen(
    viewModel: AlbumsViewModel = hiltViewModel()
){
    val albumState = viewModel.albumState
    val photoState = viewModel.photoState

    Box(modifier = Modifier.fillMaxSize().padding(5.dp)){
        LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(5.dp)){
            items(albumState.list){album ->
                AlbumItem(album = album, list = photoState.value.list.filter { it.albumId == album.id })
            }
        }

        if(albumState.error.isNotBlank()){
            Text(text = albumState.error,
            color = MaterialTheme.colors.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .align(Alignment.Center))
        }
        if(albumState.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}