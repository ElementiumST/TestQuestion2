package io.stark.testquestion.feature.first.viewmodel

import android.util.Log
import androidx.lifecycle.*
import io.stark.testquestion.feature.first.model.AnimationPlayingState
import io.stark.testquestion.feature.first.model.AnimationShowState
import io.stark.testquestion.feature.loadtimer.LoadTimer
import io.stark.testquestion.feature.loadtimer.LoadTimerBuilder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FirstViewModel : ViewModel(), LifecycleEventObserver {
    private val animationPlayingStateFlow =
        MutableStateFlow<AnimationPlayingState>(AnimationPlayingState.Play)
    private val animationShowStateFlow =
        MutableStateFlow<AnimationShowState>(AnimationShowState.Show)
    private val loadingPercentStateFlow =
        MutableStateFlow(0)

    private var timer: LoadTimer? = null

    fun getAnimationPlayingStateFlow() = animationPlayingStateFlow.asStateFlow()
    fun getAnimationShowStateFlow() = animationShowStateFlow.asStateFlow()
    fun getLoadingPercentStateFlow() = loadingPercentStateFlow.asStateFlow()

    init {
        startLoadingProcess()
    }

    private fun startLoadingProcess() {
        viewModelScope.launch {
            timer = LoadTimerBuilder()
                .setChangePercentListener {
                    loadingPercentStateFlow.emit(it)
                }
                .build()
            timer!!.start()
        }
    }

    fun startAnimation() {
        if (animationShowStateFlow.value is AnimationShowState.Show) {
            animationPlayingStateFlow.value = AnimationPlayingState.Play
        }
    }
    fun stopAnimation() {
        if (animationShowStateFlow.value is AnimationShowState.Show) {
            animationPlayingStateFlow.value = AnimationPlayingState.Stop
        }
    }
    fun changeShowState() {
        if (animationShowStateFlow.value is AnimationShowState.Show) {
            animationShowStateFlow.value = AnimationShowState.Hide
        } else {
            animationShowStateFlow.value = AnimationShowState.Show
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_PAUSE -> {
                timer?.pause()
                stopAnimation()
                Log.i(TAG, "handled pause state")
            }
            Lifecycle.Event.ON_RESUME -> {
                timer?.resume()
                startAnimation()
                Log.i(TAG, "handled resume state")
            }
            else -> {}
        }
    }
    companion object {
        const val TAG = "FirstViewModel"
    }

}