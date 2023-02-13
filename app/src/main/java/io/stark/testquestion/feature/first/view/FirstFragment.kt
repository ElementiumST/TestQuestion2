package io.stark.testquestion.feature.first.view

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import io.stark.testquestion.R
import io.stark.testquestion.databinding.FragmentFirstBinding
import io.stark.testquestion.feature.first.model.AnimationPlayingState
import io.stark.testquestion.feature.first.model.AnimationShowState
import io.stark.testquestion.feature.first.viewmodel.FirstViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private lateinit var viewModel: FirstViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater)
        viewModel = ViewModelProvider(this)[FirstViewModel::class.java]
        lifecycle.addObserver(viewModel)
        setupListeners()
        setupObservers()
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun setupObservers() {
        lifecycleScope.launchWhenResumed {
            viewModel.getAnimationPlayingStateFlow().collect {
                when(it) {
                    is AnimationPlayingState.Play -> binding.lottieAnimationView.resumeAnimation()
                    is AnimationPlayingState.Stop -> binding.lottieAnimationView.pauseAnimation()
                }
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.getAnimationShowStateFlow().collect {
                when(it) {
                    is AnimationShowState.Hide -> binding.lottieAnimationView.visibility = View.INVISIBLE
                    is AnimationShowState.Show -> binding.lottieAnimationView.visibility = View.VISIBLE
                }
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.getLoadingPercentStateFlow().collect {
                binding.progressBarPart.progressBar.progress = it
                binding.progressBarPart.LoadingPercentTextView.text = "$it%"
            }
        }
    }

    private fun setupListeners() {
        binding.startAnimation.setOnClickListener {
            viewModel.startAnimation()
        }
        binding.stopAnimation.setOnClickListener {
            viewModel.stopAnimation()
        }
        binding.changeAnimationShowState.setOnClickListener {
            viewModel.changeShowState()
        }
        binding.showAlertButton.setOnClickListener {
            FirstCustomAlertFragment.newInstance()
                .show(childFragmentManager, FirstCustomAlertFragment.TAG)
        }
        binding.goToNextScreen.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }


}