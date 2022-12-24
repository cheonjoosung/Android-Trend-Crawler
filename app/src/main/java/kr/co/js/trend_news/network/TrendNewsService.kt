package kr.co.js.trend_news.network

import kr.co.js.trend_news.model.GoogleResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TrendNewsService {

    //TODO : response json 취약성 보호를 위해 ")]}' 포함되어서 파싱이 안되는 문제 해결 필요

    @GET("trends/api/dailytrends")
    suspend fun getGoogleTrendNews(
        @Query("hl") hl:String = "ko",
        @Query("tz") tz:String = "-540",
        @Query("ed") ed:String,
        @Query("geo") geo:String = "KR",
        @Query("ns") ns:String = "15",
    ): Response<GoogleResult>
}