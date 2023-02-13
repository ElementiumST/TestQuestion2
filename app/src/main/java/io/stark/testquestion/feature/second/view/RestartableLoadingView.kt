package io.stark.testquestion.feature.second.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import io.stark.testquestion.R
import io.stark.testquestion.databinding.PartLoadDataBinding
import io.stark.testquestion.feature.dimension.toPx

class RestartableLoadingView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private val binding: PartLoadDataBinding

    init {
        binding = PartLoadDataBinding.bind(
            inflate(
                context,
                R.layout.part_load_data,
                this
            ).findViewById(R.id.rootContainer)
        )
        layoutParams = LayoutParams(context, attrs)
        binding.progressBar.layoutParams.apply {
            height = 16.toPx.toInt()
        }
    }

    fun setTitle(title: String) {
        binding.LoadingTitleTextView.text = title
    }

    fun setPercent(percent: Int) {
        binding.LoadingPercentTextView.text = "$percent%"
        binding.progressBar.progress = percent
    }

}