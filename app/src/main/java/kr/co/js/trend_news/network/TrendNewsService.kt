package kr.co.js.trend_news.network

import kr.co.js.trend_news.model.TrendResults
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TrendNewsService {

    @Headers(
        "content-type: application/json; charset=UTF-8"
    )
    @GET("dailytrends")
    suspend fun getGoogleTrendNews(
        @Query("hl") hl:String = "ko",
        @Query("tz") tz:String = "-540",
        @Query("ed") ed:String,
        @Query("geo") geo:String = "KR",
        @Query("ns") ns:String = "15",
    ): Response<TrendResults>
}