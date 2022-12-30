package kr.co.js.trend_news.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.js.trend_news.network.RemoteDataSource
import kr.co.js.trend_news.repository.GoogleRepository
import kr.co.js.trend_news.repository.NaverRepository
import kr.co.js.trend_news.ui.google.GoogleViewModel
import kr.co.js.trend_news.ui.naver.NaverViewModel

class ViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GoogleViewModel::class.java)) {
            return GoogleViewModel(
                GoogleRepository(
                    remoteDataSource = RemoteDataSource()
                )
            ) as T
        } else if (modelClass.isAssignableFrom(NaverViewModel::class.java)) {
            return NaverViewModel(
                NaverRepository(
                    remoteDataSource = RemoteDataSource()
                )
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}