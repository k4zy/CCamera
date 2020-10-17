package app.kazy.ccamera.network

import com.squareup.moshi.Json


data class SearchResponse(
    @Json(name = "result_count")
    val resultCount: Int,
    @Json(name = "page_count")
    val pageCount: Int,
    @Json(name = "page_size")
    val pageSize: Int,
    @Json(name = "results")
    val results: List<Result>
) {
    data class Result(
        @Json(name = "title")
        val title: String,
        @Json(name = "id")
        val id: String,
        @Json(name = "creator")
        val creator: String?,
        @Json(name = "creator_url")
        val creatorUrl: String,
        @Json(name = "tags")
        val tags: List<Tag>,
        @Json(name = "url")
        val url: String,
        @Json(name = "thumbnail")
        val thumbnail: String?,
        @Json(name = "provider")
        val provider: String,
        @Json(name = "source")
        val source: String,
        @Json(name = "license")
        val license: String?,
        @Json(name = "license_version")
        val licenseVersion: String,
        @Json(name = "license_url")
        val license_url: String?,
        @Json(name = "foreign_landing_url")
        val foreignLandingUrl: String,
        @Json(name = "detail_url")
        val detailUrl: String?,
        @Json(name = "related_url")
        val relatedUrl: String?,
        @Json(name = "fields_matched")
        val fieldsMatched: List<String>,
        @Json(name = "height")
        val height: Int?,
        @Json(name = "width")
        val width: Int?,
        @Json(name = "attribution")
        val attribution: String?,
    ) {
        data class Tag(
            @Json(name = "name")
            val name: String,
            @Json(name = "accuracy")
            val accuracy: Float?,
        )
    }
}
