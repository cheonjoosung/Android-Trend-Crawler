package kr.co.js.trend_news.network

import kr.co.js.trend_news.model.ResponseNaverRankModel
import retrofit2.Response
import retrofit2.http.*

interface NaverRankService {

    @FormUrlEncoded
    @POST("async/rankingMore.naver")
    suspend fun getNaverRank(
        @Field("rankingCategoryNo") rankingCategory: String,
        @Field("rookieType") rookieType: String = "ALL",
        @Field("orderType") orderType: String,
        @Field("wideThumbnail") wideThumbnail: Boolean = true,
        @Field("totalCount") totalCount: Int = -1,
        @Field("fromNo") fromNo: Int = 0,
        //@Body model: NaverRequestModel
    ): Response<ResponseNaverRankModel>
}