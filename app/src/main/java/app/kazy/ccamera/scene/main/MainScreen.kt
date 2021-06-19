package app.kazy.ccamera.scene.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp

@ExperimentalFoundationApi
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val viewState by viewModel.state.collectAsState()

    Text(text = "Hello Compose")
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(viewState.images) { photo ->
            Text(text = "Hello ${photo.title}!")
        }
    }
}
