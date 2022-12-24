package kr.co.js.trend_news.ui.google

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kr.co.js.trend_news.databinding.FragmentGoogleBinding
import kr.co.js.trend_news.model.ViewModelFactory
import kr.co.js.trend_news.ui.google.adapter.DayTrendsAdapter
import java.text.SimpleDateFormat
import java.util.*

class GoogleFragment : Fragment() {

    private var _binding: FragmentGoogleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: GoogleViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGoogleBinding.inflate(inflater, container, false)

        viewModel.getGoogleTrendingNews(getCurrentYYYYMMDD())

        viewModel.trendList.observe(viewLifecycleOwner) {

            binding.rvTrendNews.adapter = DayTrendsAdapter(it).apply {
                trendTransferListener = { trendItem ->
                    Log.e("CJS", "clicked $trendItem")
                }
            }
        }


        return binding.root
    }

    private fun getCurrentYYYYMMDD(): String {
        val formatter = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        return formatter.format(Calendar.getInstance().time)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}