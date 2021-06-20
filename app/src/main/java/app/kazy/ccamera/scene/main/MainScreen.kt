package app.kazy.ccamera.scene.main

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextLayoutInput
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.glide.rememberGlidePainter
import timber.log.Timber

@ExperimentalFoundationApi
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val viewState by viewModel.state.collectAsState()

//    val photos = viewModel.imagePagination().collectAsLazyPagingItems()
    Column() {
        var text by remember { mutableStateOf("") }
        val focusManager = LocalFocusManager.current

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("画像を検索") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
                viewModel.search(text)
            }),
            singleLine = true,
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
                .fillMaxWidth()
        )
        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 128.dp),
            contentPadding = PaddingValues(top = 10.dp, bottom = 10.dp),
        ) {
            items(viewState.images) { photo ->
                Column {
                    Image(
                        painter = rememberGlidePainter(photo.thumbnail),
                        contentDescription = photo.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(128.dp)
                            .clip(RoundedCornerShape(4.dp))
                    )
                    Text(
                        text = photo.title,
                        fontSize = 12.sp,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(
                            start = 8.dp,
                            end = 8.dp,
                            top = 4.dp,
                            bottom = 4.dp
                        )
                    )
                }
            }
        }
    }

}
