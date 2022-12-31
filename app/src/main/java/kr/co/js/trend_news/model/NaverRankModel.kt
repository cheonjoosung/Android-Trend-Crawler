package kr.co.js.trend_news.model

data class NaverRankModel(
    val title: String,
    val category: String, //포스트종류
    val writer: String, //발행한곳
    val bullet: String?, // up or down
    val bulletCount: String?, // number or new
    val href: String,
    val thumbnailUrl: String
)