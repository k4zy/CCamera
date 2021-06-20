package app.kazy.ccamera.scene.main

import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.kazy.ccamera.network.CCameraClient

class CCameraSource(private val client: CCameraClient) : PagingSource<Int, Image>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> {
        val page = params.key ?: 1
        val images = client.searchImages("tennis", page)
        return try {
            LoadResult.Page(
                data = images.results.map { it.convert() },
                prevKey = if (page == 1) null else page - 1,
                nextKey = page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Image>): Int? {
        return state.anchorPosition
    }
}
