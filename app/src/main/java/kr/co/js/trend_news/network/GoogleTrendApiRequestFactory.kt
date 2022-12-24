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

    // 유니코드에서 한글로 변환
    private fun convertStringUnicodeToKorean(data: String): String {
        // 변환할 문자를 저장할 버퍼 선언
        val sb = StringBuffer()
        // 글자를 하나하나 탐색한다.
        var i = 0
        while (i < data.length) {

            // 조합이 \u로 시작하면 6글자를 변환한다. \uxxxx
            if ('\\' == data[i] && 'u' == data[i + 1]) {
                // 그 뒤 네글자는 유니코드의 16진수 코드이다. int형으로 바꾸어서 다시 char 타입으로 강제 변환한다.
                val r = data.substring(i + 2, i + 6).toInt(16).toChar()
                // 변환된 글자를 버퍼에 넣는다.
                sb.append(r)
                // for의 증가 값 1과 5를 합해 6글자를 점프
                i += 5
            } else {
                // ascii코드면 그대로 버퍼에 넣는다.
                sb.append(data[i])
            }
            i++
        }
        // 결과 리턴
        return sb.toString()
    }

}