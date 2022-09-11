package co.kr.hoyaho.presentation.ui.categoryNews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import co.kr.hoyaho.presentation.R
import co.kr.hoyaho.presentation.databinding.FragmentCategoryNewsBinding
import co.kr.hoyaho.presentation.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryNewsFragment : Fragment() {

    private var _binding: FragmentCategoryNewsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CategoryNewsViewModel by viewModels()
    private val sharedViewModel: MainViewModel by activityViewModels()

    private val args: CategoryNewsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_category_news, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharedViewModel.updateToolbarState(
            getString(
                R.string.category_news_toolbar_title,
                args.category
            ), true
        )

        viewModel.setCategory(args.category)
    }
}
