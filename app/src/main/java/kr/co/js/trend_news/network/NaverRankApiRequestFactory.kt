package kr.co.js.trend_news.network

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object NaverRankApiRequestFactory {
    private const val basedUrl = "https://post.naver.com/"

    val retrofit: NaverRankService = Retrofit.Builder()
        .baseUrl(basedUrl)
        .addConverterFactory(GsonConverterFactory.create(
            GsonBuilder().setLenient().create()
        ))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        this.level = HttpLoggingInterceptor.Level.BODY
                    })
                .addInterceptor(NaverResponseInterceptor())
                .build()
        )
        .build()
        .create(NaverRankService::class.java)
}

class NaverResponseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8").build()
        return chain.proceed(request)
    }

}