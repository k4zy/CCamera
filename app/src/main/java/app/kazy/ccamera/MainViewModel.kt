package app.kazy.ccamera

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.kazy.ccamera.model.Image
import app.kazy.ccamera.model.convert
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    private val client: CreativeCommonsClient
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


        client = retrofit.create(CreativeCommonsClient::class.java)
    }

    fun search(word: String) {
        viewModelScope.launch {
            val searchResponse = client.searchImages(word)
            _images.postValue(searchResponse.results.map { it.convert() })
        }
    }
}
