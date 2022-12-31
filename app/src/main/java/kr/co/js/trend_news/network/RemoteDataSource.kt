package kr.co.js.trend_news.network

import kr.co.js.trend_news.model.GoogleResult
import kr.co.js.trend_news.model.ResponseNaverRankModel
import retrofit2.Response

class RemoteDataSource {

    suspend fun getGoogleTrendingNews(date: String): Response<GoogleResult> {
        return GoogleTrendApiRequestFactory.retrofit.getGoogleTrendNews(ed = date)
    }

    suspend fun getNaverRank(
        rankingCategory: String,
        orderType: String,
        fromNo: Int,
    ): Response<ResponseNaverRankModel> {
        //return NaverRankApiRequestFactory.retrofit.getNaverRank(NaverRequestModel(rankingCategory, "ALL", orderType, true, -1, fromNo))
        return NaverRankApiRequestFactory.retrofit.getNaverRank(rankingCategory = rankingCategory, orderType = orderType, fromNo = fromNo)
    }
}