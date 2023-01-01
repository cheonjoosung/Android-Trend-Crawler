package kr.co.js.trend_news.ui.naver.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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

        val rank = position + 1
        binder.tvRank.text = rank.toString()

        binder.tvTitle.text = item.title

        Log.e("CJS", "bullet ${item.bullet} ${item.bulletCount}")
        when (item.bullet) {
            "up" -> {
                val text = "▲ ${item.bulletCount}"
                binder.tvRankBullet.text = text
                binder.tvRankBullet.setTextColor(Color.RED)
            }
            "down" -> {
                val text = "▼ ${item.bulletCount}"
                binder.tvRankBullet.text = text
                binder.tvRankBullet.setTextColor(Color.BLUE)
            }
            else -> {
                if (item.bulletCount.isNullOrEmpty()) {
                    binder.tvRankBullet.text = "-"
                } else {
                    binder.tvRankBullet.text = item.bulletCount
                    binder.tvRankBullet.setTextColor(Color.GREEN)
                }
            }
        }

        binder.tvCategory.text = item.category

        binder.tvWriter.text = item.writer

        item.thumbnailUrl?.let {
            Glide.with(holder.itemView.context)
                .load(it)
                .into(binder.ivThumbnail)
        }

        binder.llRankItem.click {
            naverRankClickListener?.invoke(item)
        }
    }

    override fun getItemCount(): Int = naverRankList.size

    fun addMoreNaverRankList(moreNaverRankList: MutableList<NaverRankModel>) {
        val size = naverRankList.size
        naverRankList.addAll(moreNaverRankList)
        notifyItemRangeInserted(size, moreNaverRankList.size)
    }
}