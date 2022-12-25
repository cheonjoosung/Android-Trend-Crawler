package kr.co.js.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.js.trend_news.databinding.DialogBottomsheetBinding
import kr.co.js.trend_news.model.Article

class BottomDialogFragment(
    private val title: String,
    private val list: List<Article>,
) : BottomSheetDialogFragment() {

    var articleTransferListener: ((Article) -> Unit)? = null

    private var _binding: DialogBottomsheetBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DialogBottomsheetBinding.inflate(inflater, container, false)

        binding.tvArticleTitle.text = title

        binding.rvArticle.adapter = BottomDialogAdapter(list).apply {
            articleClickListener = {
                articleTransferListener?.invoke(it)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}