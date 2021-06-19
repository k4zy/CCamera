package app.kazy.ccamera.scene.main

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.kazy.ccamera.network.CCClient
import com.bumptech.glide.Glide
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.concurrent.Future

class MainViewModel : ViewModel() {
    private val client: CCClient
    val images: LiveData<List<Image>> get() = _images
    private val _images: MutableLiveData<List<Image>> = MutableLiveData()

    init {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.creativecommons.engineering/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        client = retrofit.create(CCClient::class.java)
    }

    fun search(word: String) {
        viewModelScope.launch {
            val searchResponse = client.searchImages(word)
            _images.postValue(searchResponse.results.map { it.convert() })
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    private suspend fun <T> Future<T>.wait(): T {
        while (!isDone)
            delay(1)
        return get()
    }

    fun saveImage(context: Context, uri: Uri) = viewModelScope.launch {
        val bitmap = loadBitmap(context, uri)
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        val fileName = File(uri.path).name
        saveImage(context, fileName, bitmap)
    }

    private suspend fun loadBitmap(context: Context, uri: Uri) = Glide.with(context)
        .asBitmap()
        .load(uri)
        .submit()
        .wait()

    private fun saveImage(context: Context, fileName: String, image: Bitmap): String? {
        var savedImagePath: String? = null
        val imageFileName = "JPEG_$fileName.jpg"
        val storageDir = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                .toString() + "/CCamera"
        )
        var success = true
        if (!storageDir.exists()) {
            success = storageDir.mkdirs()
        }
        if (success) {
            val imageFile = File(storageDir, imageFileName)
            savedImagePath = imageFile.absolutePath
            try {
                val fOut: OutputStream = FileOutputStream(imageFile)
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
                fOut.close()
            } catch (e: Exception) {
                Timber.e(e)
            }
            galleryAddPic(context, savedImagePath)
        }
        return savedImagePath
    }

    private fun galleryAddPic(context: Context, imagePath: String?) {
        imagePath?.let { path ->
            val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
            val f = File(path)
            val contentUri: Uri = Uri.fromFile(f)
            mediaScanIntent.data = contentUri
            context.sendBroadcast(mediaScanIntent)
        }
    }
}
