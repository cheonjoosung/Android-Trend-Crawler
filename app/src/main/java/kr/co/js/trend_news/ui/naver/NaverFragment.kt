package kr.co.js.trend_news.ui.naver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kr.co.js.common.click
import kr.co.js.trend_news.databinding.FragmentNaverBinding
import kr.co.js.trend_news.repository.GoogleRepository
import kr.co.js.trend_news.repository.NaverRepository

class NaverFragment : Fragment() {

    private var _binding: FragmentNaverBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(NaverViewModel::class.java)

        _binding = FragmentNaverBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.textDashboard.click {

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}