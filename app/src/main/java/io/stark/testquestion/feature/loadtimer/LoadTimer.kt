package io.stark.testquestion.feature.loadtimer

import android.util.Log
import kotlinx.coroutines.delay

// Нужно ли было создавать свою реализацию, когда есть стандартная? - Нет. Просто уж больно бедным проект получался
class LoadTimer(
    private val time: Int,
    private var listener: (suspend (percent: Int) -> Unit)?
) {
    private var onPause = false
    private var needToStop = false
    suspend fun start() {

        val millsByPercent: Int = (time / PERCENT_BY_MILLISECOND).toInt()
        var percent = 0
        while (percent < 100) {
            if (needToStop) return
            if (!onPause) {
                listener?.invoke(percent)
                percent++
            }
            delay(millsByPercent.toLong())
        }
        listener?.invoke(100)
    }
    fun pause() {
        onPause = true
    }
    fun resume() {
        onPause = false
    }
    fun stop() {
        needToStop = true
    }
    companion object {
        /**
         * Сколько процетов в 1 секунде
         * Вычисляется по формуле 100% / 1000ms
         */
        const val PERCENT_BY_MILLISECOND = 0.1F
    }
}
class LoadTimerBuilder {
    private var time: Int = LOAD_TIMER_DEFAULT_TIME_SECONDS
    private var listener: (suspend (percent: Int) -> Unit)? = null

    fun setTime(timeInSeconds: Int): LoadTimerBuilder {
        time = timeInSeconds
        return this
    }

    fun setChangePercentListener(listener: suspend (percent: Int) -> Unit): LoadTimerBuilder {
        this.listener = listener
        return this
    }

    fun build(): LoadTimer {
        return LoadTimer(time, listener)
    }

    companion object {
        const val LOAD_TIMER_DEFAULT_TIME_SECONDS = 14
    }
}