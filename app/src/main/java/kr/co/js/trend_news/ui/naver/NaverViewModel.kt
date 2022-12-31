package kr.co.js.trend_news.ui.naver

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.js.trend_news.repository.NaverRepository
import org.jsoup.Jsoup

class NaverViewModel(
    private val repository: NaverRepository,
) : ViewModel() {

    var nextKey = "0"

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

    fun getNaverRank(rankCategoryType: String, orderTime: String, rookieType: String = "ALL") {
        viewModelScope.launch {
            val response = repository.getNaverRank(rankCategoryType, orderTime, 0)

            if (response.isSuccessful) {
                response.body()?.let {
                    nextKey = it.nextFromNo

                    val doc = Jsoup.parse(it.html)

                    try {
                        val element = doc.select("li")

                        for (e in element) {
                            //Log.e("CJS", "data $i $e")

                            // href
                            val href = e.select("a").first()?.attr("href") ?: ""
                            val url = "http://post.naver.com$href"

                            // rank
                            val rank = e.select("strong[class=number]").text() ?: ""

                            // state, bullet, txt 변동사항
                            val bullet =
                                e.select("i[class=bullet]").text() ?: "" //up, down, new or empty
                            val bulletCount =
                                e.select("span[class=txt]").text() ?: "" // count or empty

                            // title
                            val title = e.select("span[class=ell]").text() ?: ""

                            // category & writer
                            val category = e.select("span[class=cate]").text() ?: ""
                            val writer = e.select("span[class=writer]").text() ?: ""

                            // thumbnailUrl
                            val thumbnailUrl =
                                e.select("div.thumb > img").first()?.attr("data-src") ?: ""

                            Log.e("CJS",
                                "$rank $title $bullet $bulletCount $category $writer $thumbnailUrl")
                        }
                    } catch (e: Exception) {
                        Log.e("CJS", "html parse error ${e.message}")
                    }

                }
            }
        }
    }
}