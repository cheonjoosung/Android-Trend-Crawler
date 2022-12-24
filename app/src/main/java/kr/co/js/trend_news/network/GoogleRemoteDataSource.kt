package kr.co.js.trend_news.network

import kr.co.js.trend_news.model.GoogleResult
import retrofit2.Response

class GoogleRemoteDataSource {

    suspend fun getGoogleTrendingNews(date: String): Response<GoogleResult> {
        return GoogleTrendApiRequestFactory.retrofit.getGoogleTrendNews(ed = date)
    }
}