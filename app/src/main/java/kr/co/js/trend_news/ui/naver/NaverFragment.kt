package kr.co.js.trend_news.ui.naver

import android.R
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kr.co.js.trend_news.databinding.FragmentNaverBinding
import kr.co.js.trend_news.model.ViewModelFactory
import kr.co.js.trend_news.ui.naver.adapter.NaverRankAdapter

class NaverFragment : Fragment() {

    private val viewModel: NaverViewModel by viewModels {
        ViewModelFactory()
    }

    private var _binding: FragmentNaverBinding? = null

    private val binding get() = _binding!!

    var selectedCategoryNumber = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentNaverBinding.inflate(inflater, container, false)

        binding.spinnerRankCategory.adapter = ArrayAdapter(requireContext(),
            R.layout.simple_spinner_item,
            viewModel.getCategoryList())

        binding.spinnerRankCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedCategoryNumber = p2

                if (viewModel.initChecked) {
                    viewModel.getNaverRank(
                        selectedCategoryNumber.toString(),
                        if (binding.rbRealTime.isChecked) "REAL_TIME" else "WEEKLY"
                    )
                }
                viewModel.initChecked = true
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        viewModel.naverRankList.observe(viewLifecycleOwner) {
            binding.rvNaverRank.adapter = NaverRankAdapter(it).apply {
                naverRankClickListener = { naverRankModel ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(naverRankModel.href))
                    startActivity(intent)
                }
            }
        }

        if (!viewModel.isInitMode) {
            Log.e("CJS", "checked ${viewModel.isInitMode}")
            viewModel.getNaverRank(
                selectedCategoryNumber.toString(),
                if (binding.rbRealTime.isChecked) "REAL_TIME" else "WEEKLY",
                isInit = true
            )
        }

        binding.rgOrderTime.setOnCheckedChangeListener { _ , _ ->
            viewModel.getNaverRank(
                selectedCategoryNumber.toString(),
                if (binding.rbRealTime.isChecked) "REAL_TIME" else "WEEKLY"
            )
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.initChecked = false
        _binding = null
    }
}