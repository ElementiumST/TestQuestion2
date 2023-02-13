package io.stark.testquestion.feature.second.viewmodel

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.*
import io.stark.testquestion.domain.models.PersonDomainBean
import io.stark.testquestion.domain.usecases.GetPersonListUseCase
import io.stark.testquestion.feature.loadtimer.LoadTimer
import io.stark.testquestion.feature.loadtimer.LoadTimerBuilder
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

class SecondViewModel(
    private val getPersonListUseCase: GetPersonListUseCase
) : ViewModel(), LifecycleEventObserver {
    private val firstProgressStateFlow = MutableStateFlow(0)
    private val secondProgressStateFlow = MutableStateFlow(0)
    private val networkLoadProcessStateFlow = MutableStateFlow(0)
    private val clockStateFlow = MutableStateFlow(CLOCK_TIME)
    private val ratingCardStateFlow = MutableStateFlow<List<PersonDomainBean>>(emptyList())

    fun getRatingCardStateFlow() = ratingCardStateFlow.asStateFlow()
    fun getClockStateFlow() = clockStateFlow.asStateFlow()
    fun getFirstProgressStateFlow() = firstProgressStateFlow.asStateFlow()
    fun getSecondProgressStateFlow() = secondProgressStateFlow.asStateFlow()
    fun getNetworkLoadProcessStateFlow() = networkLoadProcessStateFlow.asStateFlow()

    private var firstTimer: LoadTimer? = null
    private var secondTimer: LoadTimer? = null
    private var dataLoadingTimer: LoadTimer? = null
    init {
        startClock()
        loadData()
        restartProgress()
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e(TAG, throwable.stackTraceToString())
        }) {
            ratingCardStateFlow.emit(getPersonListUseCase.execute())
            dataLoadingTimer?.stop()
            networkLoadProcessStateFlow.emit(100)
        }
        viewModelScope.launch {
            dataLoadingTimer = LoadTimerBuilder()
                .setTime(LOADING_MEDIUM_DURATION_SECOND)
                .setChangePercentListener {
                    if (it == 100) return@setChangePercentListener
                    networkLoadProcessStateFlow.emit(it)
                }.build()
            dataLoadingTimer!!.start()
        }
    }

    private fun startClock() {
        viewModelScope.launch {
            object : CountDownTimer(CLOCK_TIME, ONE_SECOND_IN_MILLISECOND) {
                override fun onTick(millisUntilFinished: Long) {
                    clockStateFlow.value -= ONE_SECOND_IN_MILLISECOND
                }

                override fun onFinish() {
                    Log.i(TAG, "Clock finished!")
                }
            }.start()
        }
    }

    fun restartProgress() {
        viewModelScope.launch {
            firstTimer?.stop()
            firstTimer = createTimer(firstProgressStateFlow)
            firstTimer!!.start()
        }
        viewModelScope.launch {
            secondTimer?.stop()
            secondTimer = createTimer(secondProgressStateFlow)
            secondTimer!!.start()
        }
    }
    private fun createTimer(observingFlow: MutableStateFlow<Int>): LoadTimer {
        return  LoadTimerBuilder().setTime((5..25).random())
                .setChangePercentListener { observingFlow.emit(it) }
                .build()

    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_PAUSE -> {
                firstTimer?.pause()
                secondTimer?.pause()
            }
            Lifecycle.Event.ON_RESUME -> {
                firstTimer?.resume()
                secondTimer?.resume()
            }
            else ->{}
        }
    }

    companion object {
        const val TAG = "SecondViewModel"
        const val LOADING_MEDIUM_DURATION_SECOND = 2
        const val ONE_SECOND_IN_MILLISECOND = 1000L
        const val CLOCK_TIME = 3600 * ONE_SECOND_IN_MILLISECOND
    }
}