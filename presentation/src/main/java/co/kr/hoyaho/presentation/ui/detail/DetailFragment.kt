package co.kr.hoyaho.presentation.ui.detail

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
import co.kr.hoyaho.presentation.databinding.FragmentDetailBinding
import co.kr.hoyaho.presentation.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()
    private val sharedViewModel: MainViewModel by activityViewModels()

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setupToolbarState()
        setupThumbnail()
        initState()
        setupSavedButton()
    }

    private fun setupToolbarState() = sharedViewModel.updateToolbarState(args.news.title, true)

    private fun setupThumbnail() {
        binding.detailThumbnail.clipToOutline = true
    }

    private fun initState() {
        viewModel.setNews(args.news)
        viewModel.checkIsSaved()
    }

    private fun setupSavedButton() {
        binding.detailSave.setOnClickListener {
            viewModel.updateSavedState()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
