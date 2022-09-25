package com.timife.cypresstest.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.timife.cypresstest.R
import com.timife.cypresstest.domain.model.Album
import com.timife.cypresstest.domain.model.Photo
import com.timife.cypresstest.ui.theme.Typography

@Composable
fun AlbumItem(
    album: Album,
    list: List<Photo>
) {
    Text(text = album.title, style = Typography.h6)
    Spacer(modifier = Modifier.height(5.dp))
    LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        items(list) { photo ->
            AsyncImage(
                model = photo.thumbnailUrl,
                placeholder = painterResource(id = R.drawable.ic_image),
                contentDescription ="cypress image",
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp).clip(RoundedCornerShape(10.dp))

            )
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    Divider(modifier = Modifier.height(2.dp), thickness = 3.dp, color = Color.DarkGray)
}