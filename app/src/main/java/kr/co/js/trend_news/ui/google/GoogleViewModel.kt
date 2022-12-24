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
                Log.e("CJS", "Success data is $it")
            }

        } else {
            Log.e("CJS", "Failed")
        }
    }
}