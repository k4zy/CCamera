package app.kazy.ccamera.network

import app.kazy.ccamera.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CCameraClient {
    @Headers("Authorization: Bearer ${BuildConfig.API_KEY}")
    @GET("/v1/images")
    suspend fun searchImages(
        @Query("q") searchWord: String,
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 30
    ): SearchResponse
}
