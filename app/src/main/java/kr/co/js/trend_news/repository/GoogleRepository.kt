package kr.co.js.trend_news.repository

import kr.co.js.trend_news.model.GoogleResult
import kr.co.js.trend_news.network.GoogleRemoteDataSource
import retrofit2.Response

class GoogleRepository(
    private val remoteDataSource: GoogleRemoteDataSource
) {
    suspend fun getGoogleTrendingNews(date: String): Response<GoogleResult> {
        return remoteDataSource.getGoogleTrendingNews(date)
    }
}