package kr.co.js.trend_news.ui.google

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import kr.co.js.trend_news.model.TrendingSearchesDays
import kr.co.js.trend_news.repository.GoogleRepository

class GoogleViewModel(
    val repository: GoogleRepository
) : ViewModel() {

    private val _trendList = MutableLiveData<List<TrendingSearchesDays>>()
    val trendList: LiveData<List<TrendingSearchesDays>> = _trendList

    var nextKey = ""

    fun getGoogleTrendingNews(date: String) = viewModelScope.launch {
        val response = repository.getGoogleTrendingNews(date)

        if (response.isSuccessful) {
            Log.e("CJS", "Success")
        } else {
            Log.e("CJS", "Failed")

        }
    }
}

class GoogleTrendViewModelFactory(
    private val google: GoogleRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GoogleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GoogleViewModel(google) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}