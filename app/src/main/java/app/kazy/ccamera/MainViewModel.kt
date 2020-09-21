package app.kazy.ccamera

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

class MainViewModel : ViewModel() {
    private val client: CreativeCommonsClient

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
            Timber.d("size = ${searchResponse.results.size}")
        }
    }

}
