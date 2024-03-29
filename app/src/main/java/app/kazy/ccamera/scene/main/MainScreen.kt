package app.kazy.ccamera.scene.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@ExperimentalFoundationApi
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val viewState by viewModel.state.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var text by remember { mutableStateOf("food") }
        val focusManager = LocalFocusManager.current

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("画像を検索") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    viewModel.search(text)
                }
            ),
            singleLine = true,
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
                .fillMaxWidth()
        )
        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 120.dp),
            contentPadding = PaddingValues(top = 10.dp, bottom = 10.dp, start = 4.dp, end = 4.dp),
        ) {
            items(viewState.images) { image ->
                ImageCell(viewModel = viewModel, image = image)
            }
        }
    }
}
