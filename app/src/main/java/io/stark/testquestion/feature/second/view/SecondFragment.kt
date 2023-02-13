package io.stark.testquestion.feature.second.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import io.stark.testquestion.R
import io.stark.testquestion.databinding.FragmentSecondBinding
import io.stark.testquestion.domain.usecases.GetPersonListUseCase
import io.stark.testquestion.feature.main.view.App
import io.stark.testquestion.feature.second.view.adapters.RatingCardAdapter
import io.stark.testquestion.feature.second.viewmodel.SecondViewModel
import io.stark.testquestion.feature.second.viewmodel.SecondViewModelFactory
import java.util.*
import javax.inject.Inject

class SecondFragment : Fragment() {


    @Inject
    lateinit var viewModelFactory: SecondViewModelFactory
    private val viewModel: SecondViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater)
        setupDagger()

        lifecycle.addObserver(viewModel)

        setupViews()
        setupListeners()
        setupObservers()
        return binding.root
    }

    private fun setupViews() {
        binding.firstProgressBar.setTitle(resources.getString(R.string.random_loading_title))
        binding.secondProgressBar.setTitle(resources.getString(R.string.random_loading_2_title))
        binding.bottomLoading.LoadingTitleTextView.text = getString(R.string.load_data_from_server)
        binding.personList.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.HORIZONTAL, false
        )
        binding.personList.adapter = RatingCardAdapter()
    }

    private fun setupObservers() {
        lifecycleScope.launchWhenResumed {
            viewModel.getRatingCardStateFlow().collect {
                (binding.personList.adapter as RatingCardAdapter).updateRatingCardList(it)
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.getFirstProgressStateFlow().collect {
                binding.firstProgressBar.setPercent(it)
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.getSecondProgressStateFlow().collect {
                binding.secondProgressBar.setPercent(it)
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.getNetworkLoadProcessStateFlow().collect {
                binding.bottomLoading.LoadingPercentTextView.text = "$it%"
                binding.bottomLoading.progressBar.progress = it
            }
        }
        lifecycleScope.launchWhenResumed {
            val calender = Calendar.getInstance()
            viewModel.getClockStateFlow().collect {
                calender.timeInMillis = it
                binding.secondsValue.text = parseIntTimeToString(calender.get(Calendar.SECOND))
                binding.minutesValue.text = parseIntTimeToString(calender.get(Calendar.MINUTE))
                binding.hoursValue.text = parseIntTimeToString(calender.get(Calendar.HOUR))
            }
        }
    }

    private fun parseIntTimeToString(timeBeam: Int): String {
        return if (timeBeam.toString().length == 1)
            "0$timeBeam"
        else
            timeBeam.toString()
    }

    private fun setupListeners() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.randomizeValue.setOnClickListener {
            viewModel.restartProgress()
        }
    }

    private fun setupDagger() {
        (requireActivity().applicationContext as App)
            .appComponent
            .inject(this)
    }
}