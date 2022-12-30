package kr.co.js.trend_news.ui.naver

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kr.co.js.trend_news.databinding.FragmentNaverBinding
import kr.co.js.trend_news.model.ViewModelFactory
import kr.co.js.trend_news.ui.naver.adapter.RankCategoryAdapter

class NaverFragment : Fragment() {

    private val viewModel: NaverViewModel by viewModels {
        ViewModelFactory()
    }

    private var _binding: FragmentNaverBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNaverBinding.inflate(inflater, container, false)

        binding.rvRankCategory.adapter = RankCategoryAdapter(viewModel.getCategoryList()).apply {
            rankCategoryClickListener = { category ->
                Log.e("CJS", "Clicked $category")
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}