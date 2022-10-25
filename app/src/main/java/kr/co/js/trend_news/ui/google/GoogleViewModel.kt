package kr.co.js.trend_news.ui.google

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GoogleViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is google Fragment"
    }
    val text: LiveData<String> = _text
}