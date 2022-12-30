package kr.co.js.trend_news.repository

import kr.co.js.trend_news.model.GoogleResult
import kr.co.js.trend_news.network.RemoteDataSource
import retrofit2.Response

class GoogleRepository(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getGoogleTrendingNews(date: String): Response<GoogleResult> {
        return remoteDataSource.getGoogleTrendingNews(date)
    }
}