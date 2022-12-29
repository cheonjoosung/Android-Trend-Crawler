package kr.co.js.trend_news.ui.google

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import kr.co.js.common.BottomDialogFragment
import kr.co.js.trend_news.databinding.FragmentGoogleBinding
import kr.co.js.trend_news.model.ViewModelFactory
import kr.co.js.trend_news.ui.google.adapter.DayTrendsAdapter
import java.text.SimpleDateFormat
import java.util.*


class GoogleFragment : Fragment() {

    private var _binding: FragmentGoogleBinding? = null

    private val binding get() = _binding!!

    private val viewModel: GoogleViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    lateinit var adapter: DayTrendsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGoogleBinding.inflate(inflater, container, false)


        if (::adapter.isInitialized) {
            binding.rvTrendNews.adapter = adapter
        } else {
            viewModel.getGoogleTrendingNews(getCurrentYYYYMMDD())

            viewModel.trendList.observe(viewLifecycleOwner) {
                adapter = DayTrendsAdapter(it).apply {
                    trendTransferListener = { trendItem ->

                        val bottomDialogFragment =
                            BottomDialogFragment(trendItem.title.query, trendItem.articles).apply {
                                articleTransferListener = { article ->
                                    this.dismiss()

                                    val browserIntent =
                                        Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                                    startActivity(browserIntent)
                                }
                            }

                        bottomDialogFragment.show(childFragmentManager, "TAG")
                    }
                }
                binding.rvTrendNews.adapter = adapter
            }

        }

        viewModel.moreTrendList.observe(viewLifecycleOwner) {
            adapter.addMoreTendList(it)
        }

        binding.rvTrendNews.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.getGoogleMoreTrendingNews()
                }
            }
        })


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