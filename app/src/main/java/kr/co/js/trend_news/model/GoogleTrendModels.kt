package kr.co.js.trend_news.model

import com.google.gson.annotations.SerializedName

data class GoogleResult(
    @SerializedName("default")
    val default: TrendResults,
)

data class TrendResults(
    @SerializedName("endDateForNextRequest") //nextKey
    val endDateForNextRequest: String,

    @SerializedName("rssFeedPageUrl")
    val rssFeedPageUrl: String,

    @SerializedName("trendingSearchesDays")
    val trendingSearchesDays: List<TrendingSearchesDays>,
)

// day 트렌딩 목록
data class TrendingSearchesDays(
    @SerializedName("date")
    val date: String,

    @SerializedName("formattedDate")
    val formattedDate: String,

    @SerializedName("trendingSearches")
    val trendingSearches: List<TrendingSearches>,
)

// 트렌딩 뉴스 아이템
data class TrendingSearches(
    @SerializedName("articles")
    val articles: List<Article>,

    @SerializedName("formattedTraffic")
    val formattedTraffic: String,

    @SerializedName("image")
    val image: TrendImage?,

    /*@SerializedName("relatedQueries")
    val relatedQueries: List<Query>,*/

    @SerializedName("shareUrl")
    val shareUrl: String,

    @SerializedName("title")
    val title: TrendTitle,
)

// 기사 아이템
data class Article(
    @SerializedName("title")
    val title: String,

    @SerializedName("image")
    val image: TrendImage?,

    @SerializedName("snippet")
    val snippet: String,

    @SerializedName("source")
    val source: String,

    @SerializedName("timeAgo")
    val timeAgo: String,

    @SerializedName("url")
    val url: String,
)

data class TrendImage(
    @SerializedName("imageUrl")
    val imageUrl: String,

    @SerializedName("newsUrl")
    val newsUrl: String,

    @SerializedName("source")
    val source: String,
)

data class TrendTitle(
    @SerializedName("query")
    val query: String,

    @SerializedName("exploreLink")
    val exploreLink: String,
)