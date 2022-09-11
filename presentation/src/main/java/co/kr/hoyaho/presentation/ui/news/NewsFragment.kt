package co.kr.hoyaho.presentation.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.kr.hoyaho.domain.model.News
import co.kr.hoyaho.presentation.R
import co.kr.hoyaho.presentation.databinding.FragmentNewsBinding
import co.kr.hoyaho.presentation.ui.adapter.NewsAdapter
import co.kr.hoyaho.presentation.ui.adapter.NewsClickListener
import co.kr.hoyaho.presentation.ui.main.MainViewModel
import co.kr.hoyaho.presentation.ui.util.EventObserver
import co.kr.hoyaho.presentation.ui.util.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment(), NewsClickListener {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NewsViewModel by viewModels()
    private val sharedViewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: NewsAdapter

    private val loadingDialog: LoadingDialog by lazy {
        LoadingDialog(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharedViewModel.updateToolbarState(getString(R.string.news_toolbar_title), false)

        adapter = NewsAdapter().apply { setItemClickListener(this@NewsFragment) }

        binding.newsRcv.apply {
            this.adapter = this@NewsFragment.adapter
            this.layoutManager = LinearLayoutManager(requireActivity())
            this.setHasFixedSize(true)
        }

        viewModel.showToast.observe(viewLifecycleOwner, EventObserver { msg ->
            Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
        })

        viewModel.news.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) loadingDialog.show()
            else loadingDialog.dismiss()
        }

        viewModel.isError.observe(viewLifecycleOwner) {
            if (it) {
                binding.newsRcv.visibility = View.GONE
                binding.newsErrorRefresh.root.visibility = View.VISIBLE
            }
            else {
                binding.newsRcv.visibility = View.VISIBLE
                binding.newsErrorRefresh.root.visibility = View.GONE
            }
        }

        binding.newsErrorRefresh.refresh.setOnClickListener {
            viewModel.fetchNews()
        }

        binding.newsSwipe.setOnRefreshListener {
            viewModel.fetchNews()
            binding.newsSwipe.isRefreshing = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun navigateToDetail(news: News) {
        findNavController().navigate(NewsFragmentDirections.actionNewsToDetail(news))
    }
}
