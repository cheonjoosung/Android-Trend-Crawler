package kr.co.js.trend_news.ui.google

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import kr.co.js.trend_news.model.TrendingSearchesDays
import kr.co.js.trend_news.repository.GoogleRepository

class GoogleViewModel(
    val repository: GoogleRepository,
) : ViewModel() {

    private val _trendList = MutableLiveData<List<TrendingSearchesDays>>()
    val trendList: LiveData<List<TrendingSearchesDays>> = _trendList

    var nextKey = ""

    fun getGoogleTrendingNews(date: String) = viewModelScope.launch {
        val response = repository.getGoogleTrendingNews(date)

        if (response.isSuccessful) {

            response.body()?.let {
                val data = it.string().replace(")]}',", "").trim()
                Log.e("CJS", "Success data is $data")
                Log.e("CJS", "Convert data is ${convertStringUnicodeToKorean(data)}")
                //Log.e("CJS", "replace data is ${data.replace(")]}',", "").trim()}")
            }

        } else {
            Log.e("CJS", "Failed")
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

class GoogleTrendViewModelFactory(
    private val google: GoogleRepository,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GoogleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GoogleViewModel(google) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}