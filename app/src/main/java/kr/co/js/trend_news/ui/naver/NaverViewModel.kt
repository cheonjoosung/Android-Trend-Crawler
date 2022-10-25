package kr.co.js.trend_news.ui.naver

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NaverViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is naver Fragment"
    }
    val text: LiveData<String> = _text
}