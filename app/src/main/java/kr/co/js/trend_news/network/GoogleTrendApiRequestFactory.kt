package kr.co.js.trend_news.network

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object GoogleTrendApiRequestFactory {
    private const val basedUrl = "https://trends.google.co.kr/"

    val retrofit: TrendNewsService = Retrofit.Builder()
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
                .addInterceptor(ResponseInterceptor())
                .build()
        )
        .build()
        .create(TrendNewsService::class.java)
}

class ResponseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        // 데이터의 정상 여부에 따라 결과 반환
        return if (response.code == 200) {
            val bodyString = response.body?.string()?.replace(")]}',", "")?.trim() ?: ""
            val convertedBody = convertStringUnicodeToKorean(bodyString)

            val body =
                convertedBody.toResponseBody("application/json; charset=utf-8".toMediaTypeOrNull())
            response.newBuilder().body(body).build()
        } else {
            response
        }
    }

    private fun convertStringUnicodeToKorean(data: String): String {

        val sb = StringBuilder() // 단일 쓰레드이므로 StringBuilder 선언
        var i = 0

        /**
         * \uXXXX 로 된 아스키코드 변경
         * i+2 to i+6 을 16진수의 int 계산 후 char 타입으로 변환
         */
        while (i < data.length) {

            if (data[i] == '\\' && data[i + 1] == 'u') {

                val word = data.substring(i + 2, i + 6).toInt(16).toChar()
                sb.append(word)
                i += 5
            } else {
                sb.append(data[i])
            }

            i++
        }

        return sb.toString()
    }

}