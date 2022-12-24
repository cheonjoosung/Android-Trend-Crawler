package kr.co.js.common

import android.view.View
import androidx.lifecycle.MutableLiveData

// for mutable list
operator fun <T> MutableLiveData<MutableList<T>>.plusAssign(item: T) {
    val value = this.value ?: mutableListOf()
    value.add(item)
    this.value = value
}

// 중복클릭 방지
fun View.click(click: (View) -> Unit) {
    val listener = DuplicateClickListener { click(it) }
    setOnClickListener(listener)
}