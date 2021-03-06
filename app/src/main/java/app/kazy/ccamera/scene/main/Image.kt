package app.kazy.ccamera.scene.main

import android.net.Uri
import androidx.core.net.toUri
import app.kazy.ccamera.network.SearchResponse

data class Image(
    val title: String,
    val url: Uri,
    val thumbnail: Uri?,
)

fun SearchResponse.Result.convert() = Image(
    title = title,
    url = url.toUri(),
    thumbnail = thumbnail?.toUri()
)
