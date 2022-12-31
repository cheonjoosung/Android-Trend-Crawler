package kr.co.js.trend_news.repository

import kr.co.js.trend_news.model.ResponseNaverRankModel
import kr.co.js.trend_news.network.RemoteDataSource
import retrofit2.Response

class NaverRepository(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getNaverRank(rankingCategory: String, orderType: String, fromNo: Int): Response<ResponseNaverRankModel> {
        return remoteDataSource.getNaverRank(rankingCategory, orderType, fromNo)
    }
}