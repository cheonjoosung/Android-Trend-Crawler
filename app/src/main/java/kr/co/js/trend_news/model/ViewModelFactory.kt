package kr.co.js.trend_news.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.js.trend_news.network.GoogleRemoteDataSource
import kr.co.js.trend_news.repository.GoogleRepository
import kr.co.js.trend_news.ui.google.GoogleViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GoogleViewModel::class.java)) {
            return GoogleViewModel(
                GoogleRepository(
                    remoteDataSource = GoogleRemoteDataSource()
                )
            ) as T
        } /*else if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(
                RegisterRepository(
                    remoteDataSource = IssueRemoteDataSource(),
                    localDataSource = IssueLocalDataSource(context)
                )
            ) as T
        }
        }*/

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}