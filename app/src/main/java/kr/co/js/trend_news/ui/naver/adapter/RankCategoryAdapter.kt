package kr.co.js.trend_news.ui.naver.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.js.common.click
import kr.co.js.trend_news.databinding.ItemRankCategoryBinding
import kr.co.js.trend_news.ui.naver.RankCategory

class RankCategoryAdapter(
    private val rankCategoryMutableList: MutableList<RankCategory>,
) : RecyclerView.Adapter<RankCategoryAdapter.RankCategoryViewHolder>() {

    var rankCategoryClickListener: ((RankCategory) -> Unit)? = null

    class RankCategoryViewHolder(val binding: ItemRankCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankCategoryViewHolder {
        return RankCategoryViewHolder(ItemRankCategoryBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: RankCategoryViewHolder, position: Int) {
        val binder = holder.binding
        val item = rankCategoryMutableList[position]

        binder.tvTitle.text = item.rankName

        binder.tvTitle.click {
            rankCategoryClickListener?.invoke(item)
        }
    }

    override fun getItemCount(): Int = rankCategoryMutableList.size
}