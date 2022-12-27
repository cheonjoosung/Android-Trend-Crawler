package kr.co.js.trend_news.ui.google

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.js.trend_news.model.TrendingSearchesDays
import kr.co.js.trend_news.repository.GoogleRepository

class GoogleViewModel(
    private val repository: GoogleRepository,
) : ViewModel() {

    private val _trendList = MutableLiveData<MutableList<TrendingSearchesDays>>()
    val trendList: MutableLiveData<MutableList<TrendingSearchesDays>> = _trendList

    private val _moreTrendList = MutableLiveData<MutableList<TrendingSearchesDays>>()
    val moreTrendList: MutableLiveData<MutableList<TrendingSearchesDays>> = _moreTrendList

    private var nextKey = ""

    fun getGoogleTrendingNews(date: String) =
        viewModelScope.launch {
            val response =
                repository.getGoogleTrendingNews(date)

            if (response.isSuccessful) {

                response.body()?.default?.let {
                    nextKey = it.endDateForNextRequest
                    _trendList.postValue(it.trendingSearchesDays.toMutableList())
                }

            } else {
                Log.e("CJS", "Failed")
            }
        }

    fun getGoogleMoreTrendingNews() =
        viewModelScope.launch {
            if (nextKey.isEmpty()) return@launch

            val response =
                repository.getGoogleTrendingNews(nextKey)

            if (response.isSuccessful) {

                response.body()?.default?.let {
                    nextKey = it.endDateForNextRequest
                    _moreTrendList.postValue(it.trendingSearchesDays.toMutableList())
                }

            } else {
                Log.e("CJS", "Failed")
            }
        }
}