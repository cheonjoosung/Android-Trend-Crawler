package kr.co.js.trend_news.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object GoogleTrendApiRequestFactory {
    private const val basedUrl = "https://trends.google.co.kr/trends/api/"

    val retrofit: TrendNewsService = Retrofit.Builder()
        .baseUrl(basedUrl)
        .addConverterFactory(GsonConverterFactory.create(
            GsonBuilder().setLenient().create()
        ))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY }

            ).build())
        .build()
        .create(TrendNewsService::class.java)

}