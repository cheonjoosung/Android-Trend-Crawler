package kr.co.js.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.js.trend_news.R
import kr.co.js.trend_news.model.Article

class BottomDialogFragment(
    private val list: List<Article>
) : BottomSheetDialogFragment() {

    var articleTransferListener: ((Article) -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_bottomsheet,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.recyclerView).adapter = BottomDialogAdapter(list).apply {
            articleClickListener = {
                articleTransferListener?.invoke(it)
            }
        }
    }
}