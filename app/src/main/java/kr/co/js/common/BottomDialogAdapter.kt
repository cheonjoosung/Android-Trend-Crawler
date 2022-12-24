package kr.co.js.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.js.trend_news.databinding.ItemBottomDialogBinding
import kr.co.js.trend_news.model.Article

class BottomDialogAdapter(
    private val itemList: List<Article>,
) : RecyclerView.Adapter<BottomDialogAdapter.DialogHolder>() {

    var articleClickListener: ((Article) -> Unit)? = null

    class DialogHolder(val binding: ItemBottomDialogBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogHolder {
        return DialogHolder(ItemBottomDialogBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: DialogHolder, position: Int) {
        val binder = holder.binding
        val item = itemList[position]

        binder.tvTitle.text = item.title
        binder.tvTimeAgo.text = item.timeAgo

        if (item.image != null && item.image.imageUrl != null) {
            Glide.with(holder.itemView.context)
                .load(item.image.imageUrl)
                .into(binder.ivThumbnail)
        }

        binder.llArticle.click {
            articleClickListener?.invoke(item)
        }
    }

}