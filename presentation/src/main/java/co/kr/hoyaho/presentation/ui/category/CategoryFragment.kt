package co.kr.hoyaho.presentation.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import co.kr.hoyaho.presentation.R
import co.kr.hoyaho.presentation.databinding.FragmentCategoryBinding
import co.kr.hoyaho.presentation.ui.adapter.CategoryAdapter
import co.kr.hoyaho.presentation.ui.main.MainViewModel
import co.kr.hoyaho.presentation.ui.util.GridItemOffsetDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: MainViewModel by activityViewModels()

    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolbarState()
        setupAdapter()
    }

    private fun setupToolbarState() =
        sharedViewModel.updateToolbarState(getString(R.string.category_toolbar_title), false)

    private fun setupAdapter() {
        adapter = CategoryAdapter()

        binding.categoryRcv.apply {
            this.adapter = this@CategoryFragment.adapter
            this.layoutManager = GridLayoutManager(requireActivity(), 3)
            this.addItemDecoration(GridItemOffsetDecoration(36, 29))
            this.setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
