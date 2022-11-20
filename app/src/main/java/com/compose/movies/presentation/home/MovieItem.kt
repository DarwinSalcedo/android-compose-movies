package com.compose.movies.presentation.ui.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.compose.movies.domain.model.Show


@Composable
fun MovieItems() {
    LazyColumn(contentPadding = PaddingValues(4.dp)) {
        items(10) {
            MovieItem(Show(1, " XXX ", "", 1.00, ""))
        }
    }
}

@Composable
fun MovieItem(data: Show) {
    Column {
        Box(modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()) {
            AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                .data(data.posterPath)
                .crossfade(1000)
                .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondary)
                .padding(16.dp)) {
            Text(data.name, style = MaterialTheme.typography.titleLarge)
        }
    }
}