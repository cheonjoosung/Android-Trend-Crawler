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

    private var nextKey = ""

    fun getGoogleTrendingNews(date: String, isMoreTrendingNews: Boolean = false) =
        viewModelScope.launch {
            val response =
                repository.getGoogleTrendingNews(if (isMoreTrendingNews) nextKey else date)

            if (response.isSuccessful) {

                response.body()?.default?.let {
                    nextKey = it.endDateForNextRequest

                    val value = _trendList.value ?: mutableListOf()
                    value.addAll(it.trendingSearchesDays)
                    _trendList.postValue(value)
                }

            } else {
                Log.e("CJS", "Failed")
            }
        }
}