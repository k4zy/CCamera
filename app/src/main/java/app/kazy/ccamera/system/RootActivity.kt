package app.kazy.ccamera.system

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import app.kazy.ccamera.network.CCClient
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RootActivity : AppCompatActivity() {

    @Inject
    lateinit var ccClient: CCClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Greeting(name = "compose")
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoGrid() {

//    val state = remember { getRepositoryList() }.collectAsState(initial = null)

    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 128.dp)
    ) {
//        state.value?.let {
//            items(it.resultCount,it.results) { photo ->
//                Text(text = "Hello $photo!")
//            }
//        }
    }
}
