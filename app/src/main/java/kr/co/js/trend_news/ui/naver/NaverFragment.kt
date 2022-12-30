package kr.co.js.trend_news.ui.naver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kr.co.js.trend_news.databinding.FragmentNaverBinding
import kr.co.js.trend_news.model.ViewModelFactory

class NaverFragment : Fragment() {

    private val viewModel: NaverViewModel by viewModels {
        ViewModelFactory()
    }

    private var _binding: FragmentNaverBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentNaverBinding.inflate(inflater, container, false)

        binding.spinnerRankCategory.adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_item,
            viewModel.getCategoryList())

        binding.spinnerRankCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                getNaverRank()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        binding.rgOrderTime.setOnCheckedChangeListener { _ , _ ->
            getNaverRank()
        }



        return binding.root
    }

    fun getNaverRank() {
        val rankCategoryType = binding.spinnerRankCategory.selectedItemId
        val orderTime = if (binding.rbRealTime.isChecked) "REAL_TIME" else "WEEKLY"

        viewModel.getNaverRank(rankCategoryType.toString(), orderTime)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}