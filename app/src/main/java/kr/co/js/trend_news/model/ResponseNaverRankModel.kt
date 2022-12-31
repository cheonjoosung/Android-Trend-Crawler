package kr.co.js.trend_news.model

import com.google.gson.annotations.SerializedName

data class ResponseNaverRankModel(
    @SerializedName("resultCode")
    val resultCode: String,

    @SerializedName("fromNo")
    val fromNo: String,

    @SerializedName("nextFromNo")
    val nextFromNo: String,

    @SerializedName("listCount")
    val listCount: String,

    @SerializedName("totalCount")
    val totalCount: String,

    @SerializedName("html")
    val html: String
)