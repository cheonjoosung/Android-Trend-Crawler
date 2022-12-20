package kr.co.js.trend_news.network

import kr.co.js.trend_news.model.TrendResults
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TrendNewsService {

    @GET("dailytrends")
    suspend fun getGoogleTrendNews(
        @Query("hl") hl:String = "ko",
        @Query("tz") tz:String = "-540",
        @Query("ed") ed:String,
        @Query("get") get:String = "KR",
        @Query("ed") ns:String = "15",
    ): Response<TrendResults>

    /*@GET("search")
    suspend fun getYouTubeVideos(
        @Query("key") apiKey: String,
        @Query("q") query: String,
        @Query("order") videoOrder: String,
        @Query("type") videoType: String = "video",
        @Query("maxResults") maxResults: Int,
        @Query("channelId") channelId: String = "",
        @Query("part") part: String = "snippet",
    ): Response<YoutubeVideo>*/
}