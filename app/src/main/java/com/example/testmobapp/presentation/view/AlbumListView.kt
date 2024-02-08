package com.example.testmobapp.presentation.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testmobapp.data.model.Album
import com.example.testmobapp.presentation.viewmodel.MainViewModel

@Composable
fun AlbumListView() {
    val vm: MainViewModel = viewModel<MainViewModel>()

    if (vm.albumList.value == null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(100.dp),
                color = Color.Blue
            )
        }
    } else {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            vm.albumList.value?.forEach { album ->
                item { AlbumItemView(album = album) }
            }
        }

    }
}


@Composable
fun AlbumItemView(album: Album) {
    Column(
        modifier = Modifier

            .clip(RoundedCornerShape(10))

            .border(
                BorderStroke(1.dp, Color.Black),
                RoundedCornerShape(20)
            )

            .fillMaxWidth()
            .padding(16.dp)
    ) {
//        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = album.title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
        Text(text = album.artist, style = MaterialTheme.typography.bodyLarge)
        Text(text = "$" + album.price.toString(), style = MaterialTheme.typography.bodyMedium)
    }

}

@Composable
@Preview
fun AlbumListViewPrev() {
    AlbumListView()
}