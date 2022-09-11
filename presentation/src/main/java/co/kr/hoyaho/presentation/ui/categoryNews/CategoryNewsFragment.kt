package co.kr.hoyaho.presentation.ui.categoryNews

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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import co.kr.hoyaho.domain.model.News
import co.kr.hoyaho.presentation.R
import co.kr.hoyaho.presentation.databinding.FragmentCategoryNewsBinding
import co.kr.hoyaho.presentation.ui.adapter.NewsAdapter
import co.kr.hoyaho.presentation.ui.adapter.NewsClickListener
import co.kr.hoyaho.presentation.ui.main.MainViewModel
import co.kr.hoyaho.presentation.ui.util.EventObserver
import co.kr.hoyaho.presentation.ui.util.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryNewsFragment : Fragment(), NewsClickListener {

    private var _binding: FragmentCategoryNewsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CategoryNewsViewModel by viewModels()
    private val sharedViewModel: MainViewModel by activityViewModels()

    private val args: CategoryNewsFragmentArgs by navArgs()

    private lateinit var adapter: NewsAdapter

    private val loadingDialog: LoadingDialog by lazy {
        LoadingDialog(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_category_news, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolbarState()
        setupAdapter()
        initState()
        setupDataBinding()
        setupRefresh()
    }

    private fun setupToolbarState() = sharedViewModel.updateToolbarState(
        getString(
            R.string.category_news_toolbar_title,
            args.category
        ), true
    )

    private fun setupAdapter() {
        adapter = NewsAdapter().apply { setItemClickListener(this@CategoryNewsFragment) }

        binding.categoryNewsRcv.apply {
            this.adapter = this@CategoryNewsFragment.adapter
            this.layoutManager = LinearLayoutManager(requireActivity())
            this.setHasFixedSize(true)
        }
    }

    private fun initState() = viewModel.setCategory(args.category)

    private fun setupDataBinding() {
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
                binding.categoryNewsRcv.visibility = View.GONE
                binding.categoryNewsErrorRefresh.root.visibility = View.VISIBLE
            } else {
                binding.categoryNewsRcv.visibility = View.VISIBLE
                binding.categoryNewsErrorRefresh.root.visibility = View.GONE
            }
        }
    }

    private fun setupRefresh() {
        binding.categoryNewsErrorRefresh.refresh.setOnClickListener {
            viewModel.refreshCategoryNews()
        }

        binding.categoryNewsSwipe.setOnRefreshListener {
            viewModel.refreshCategoryNews()
            binding.categoryNewsSwipe.isRefreshing = false
        }
    }

    override fun navigateToDetail(news: News) {
        findNavController().navigate(CategoryNewsFragmentDirections.actionCategoryNewsToDetail(news))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
