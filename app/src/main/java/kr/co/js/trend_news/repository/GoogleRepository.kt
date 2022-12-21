package kr.co.js.trend_news.repository

import kr.co.js.trend_news.model.GoogleResult
import kr.co.js.trend_news.model.TrendResults
import kr.co.js.trend_news.network.GoogleRemoteDataSource
import kr.co.js.trend_news.network.TrendNewsService
import okhttp3.ResponseBody
import retrofit2.Response

class GoogleRepository(
    private val remoteDataSource: GoogleRemoteDataSource
) {
    suspend fun getGoogleTrendingNews(date: String): Response<ResponseBody> {
        return remoteDataSource.getGoogleTrendingNews(date)
    }
}