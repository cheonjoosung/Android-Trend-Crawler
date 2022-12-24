package kr.co.js.trend_news.ui.google.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.js.common.click
import kr.co.js.trend_news.databinding.ItemTrendBinding
import kr.co.js.trend_news.model.TrendingSearches

/**
 * 트렌드 아이템 처리하는 어댑터
 */
class TrendAdapter(
    private val trendList: MutableList<TrendingSearches>,
) : RecyclerView.Adapter<TrendAdapter.TrendViewHolder>() {

    var trendClickListener: ((TrendingSearches) -> Unit)? = null

    class TrendViewHolder(val binding: ItemTrendBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendViewHolder {
        return TrendViewHolder(ItemTrendBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        val binder = holder.binding
        val item = trendList[position]

        val rank = position + 1
        binder.tvRank.text = rank.toString()
        binder.tvTitle.text = item.title.query
        binder.tvViewCount.text = item.formattedTraffic

        Glide.with(holder.itemView.context)
            .load(item.image.imageUrl)
            .into(binder.ivThumbnail)

        binder.llTrendItem.click {
            trendClickListener?.invoke(item)
        }
    }

    override fun getItemCount(): Int = trendList.size
}