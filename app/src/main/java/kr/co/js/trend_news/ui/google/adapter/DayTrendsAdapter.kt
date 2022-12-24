package kr.co.js.trend_news.ui.google.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.js.trend_news.databinding.ItemDayTrendsBinding
import kr.co.js.trend_news.model.TrendingSearchesDays

/**
 * 날짜와 트렌드 목록을 처리하는 어댑터
 */
class DayTrendsAdapter(
    private val dayTrendsList: MutableList<TrendingSearchesDays>,
) : RecyclerView.Adapter<DayTrendsAdapter.DaysTrendsViewHolder>() {

    class DaysTrendsViewHolder(val binding: ItemDayTrendsBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysTrendsViewHolder {
        return DaysTrendsViewHolder(ItemDayTrendsBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: DaysTrendsViewHolder, position: Int) {

        val binder = holder.binding
        val item = dayTrendsList[position]

        binder.tvDate.text = item.formattedDate
    }

    override fun getItemCount(): Int = dayTrendsList.size
}