package app.kazy.ccamera

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import app.kazy.ccamera.scene.main.MainScreen
import app.kazy.ccamera.scene.main.MainViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CCameraApp() {
    MaterialTheme() {
        val mainViewModel = hiltViewModel<MainViewModel>()
        MainScreen(mainViewModel)
    }
}
