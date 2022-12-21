package kr.co.js.trend_news.network

import kr.co.js.trend_news.model.GoogleResult
import kr.co.js.trend_news.model.TrendResults
import okhttp3.ResponseBody
import retrofit2.Response

class GoogleRemoteDataSource {

    suspend fun getGoogleTrendingNews(date: String): Response<ResponseBody> {
        return GoogleTrendApiRequestFactory.retrofit.getGoogleTrendNews(ed = date)
    }
}