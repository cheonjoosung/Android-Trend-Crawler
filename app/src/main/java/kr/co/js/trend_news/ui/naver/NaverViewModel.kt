package kr.co.js.trend_news.ui.naver

import androidx.lifecycle.ViewModel
import kr.co.js.trend_news.repository.NaverRepository

class NaverViewModel(
    val repository: NaverRepository,
) : ViewModel() {

    private val rankCategories = arrayOf("전체",
        "여행",
        "문화&예술",
        "핫이슈",
        "차&테크",
        "패션&뷰티",
        "어학&강좌",
        "푸드",
        "육아",
        "리빙",
        "게임",
        "공감&에세이")

    fun getCategoryList() = rankCategories

    fun getNaverRank(rankCategoryType: String, orderTime: String) {

    }
}