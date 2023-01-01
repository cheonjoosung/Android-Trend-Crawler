package kr.co.js.trend_news.ui.naver.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.js.common.click
import kr.co.js.trend_news.databinding.ItemNaverRankBinding
import kr.co.js.trend_news.model.NaverRankModel

class NaverRankAdapter(
    private val naverRankList: MutableList<NaverRankModel>,
) : RecyclerView.Adapter<NaverRankAdapter.NaverRankViewHolder>() {

    var naverRankClickListener: ((NaverRankModel) -> Unit)? = null

    class NaverRankViewHolder(val binding: ItemNaverRankBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NaverRankViewHolder {
        return NaverRankViewHolder(ItemNaverRankBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: NaverRankViewHolder, position: Int) {
        val binder = holder.binding
        val item = naverRankList[position]

        binder.tvRank.text = position.toString()
    }

    override fun getItemCount(): Int = naverRankList.size

    fun addMoreNaverRankList(moreNaverRankList: MutableList<NaverRankModel>) {
        val size = naverRankList.size
        naverRankList.addAll(moreNaverRankList)
        notifyItemRangeInserted(size, moreNaverRankList.size)
    }
}