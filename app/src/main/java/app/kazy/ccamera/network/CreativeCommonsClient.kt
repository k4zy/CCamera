package app.kazy.ccamera.network

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CreativeCommonsClient {
    @Headers("Authorization: Bearer DLBYIcfnKfolaXKcmMC8RIDCavc2hW")
    @GET("/v1/images")
    suspend fun searchImages(@Query("q") searchWord: String): SearchResponse
}
