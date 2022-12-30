package kr.co.js.trend_news.ui.naver

import androidx.lifecycle.ViewModel
import kr.co.js.trend_news.repository.NaverRepository

class NaverViewModel(
    val repository: NaverRepository
) : ViewModel() {

    fun getCategoryList(): MutableList<RankCategory> {
        return mutableListOf<RankCategory>().apply {
            add(RankCategory("전체", "0"))
            add(RankCategory("여행", "1"))
            add(RankCategory("문화&예술", "2"))
            add(RankCategory("핫이슈", "3"))
            add(RankCategory("차&테크", "4"))
            add(RankCategory("패션&뷰티", "5"))
            add(RankCategory("어학&강좌", "6"))
            add(RankCategory("푸드", "7"))
            add(RankCategory("육아", "8"))
            add(RankCategory("리빙", "9"))
            add(RankCategory("게임", "10"))
            add(RankCategory("공감&에세이", "11"))
        }


    }
}

data class RankCategory(val rankName: String, val rankNumber: String)