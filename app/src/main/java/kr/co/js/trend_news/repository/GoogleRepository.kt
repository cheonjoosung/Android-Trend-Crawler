package kr.co.js.trend_news.repository

import kr.co.js.trend_news.model.TrendResults
import kr.co.js.trend_news.network.GoogleRemoteDataSource
import kr.co.js.trend_news.network.TrendNewsService
import retrofit2.Response

class GoogleRepository(
    private val remoteDataSource: GoogleRemoteDataSource
) {
    suspend fun getGoogleTrendingNews(date: String): Response<TrendResults> {
        return remoteDataSource.getGoogleTrendingNews(date)
    }
}