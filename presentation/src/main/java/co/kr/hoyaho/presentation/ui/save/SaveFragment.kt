package co.kr.hoyaho.presentation.ui.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.kr.hoyaho.domain.model.News
import co.kr.hoyaho.presentation.R
import co.kr.hoyaho.presentation.databinding.FragmentSaveBinding
import co.kr.hoyaho.presentation.ui.adapter.NewsAdapter
import co.kr.hoyaho.presentation.ui.adapter.NewsClickListener
import co.kr.hoyaho.presentation.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaveFragment : Fragment(), NewsClickListener {

    private var _binding: FragmentSaveBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SaveViewModel by viewModels()
    private val sharedViewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_save, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolbarState()
        setupAdapter()
        initState()
        setupDataBinding()
    }

    private fun setupToolbarState() =
        sharedViewModel.updateToolbarState(getString(R.string.save_toolbar_title), false)

    private fun initState() = viewModel.getSavedNews()

    private fun setupAdapter() {
        adapter = NewsAdapter().apply { setItemClickListener(this@SaveFragment) }

        binding.saveRcv.apply {
            this.adapter = this@SaveFragment.adapter
            this.layoutManager = LinearLayoutManager(requireActivity())
            this.setHasFixedSize(true)
        }
    }

    private fun setupDataBinding() {
        viewModel.news.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.saveRcv.visibility = View.GONE
                binding.saveEmptyNews.visibility = View.VISIBLE
            } else {
                binding.saveRcv.visibility = View.VISIBLE
                binding.saveEmptyNews.visibility = View.GONE
                adapter.submitList(it)
            }
        }
    }

    override fun navigateToDetail(news: News) {
        findNavController().navigate(SaveFragmentDirections.actionSaveToDetail(news))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
