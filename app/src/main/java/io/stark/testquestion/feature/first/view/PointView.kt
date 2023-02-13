package io.stark.testquestion.feature.first.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import io.stark.testquestion.R
import io.stark.testquestion.databinding.ViewPointBinding

class PointView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private val binding: ViewPointBinding
    private var pointDrawableId: Int = -1
    private var lightColorId: Int = -1
    private var pointDescription: String? = null

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.PointView,
            0, 0
        ).apply {
            runCatching {
                pointDrawableId = getResourceId(R.styleable.PointView_pointDrawable, -1)
                pointDescription = getString(R.styleable.PointView_pointDescription)
                lightColorId = getColor(R.styleable.PointView_pointLightColor, -1)
            }.also {
                recycle()
            }
        }
        layoutParams = LayoutParams(context, attrs)
        binding = ViewPointBinding.bind(
            inflate(
                context,
                R.layout.view_point,
                this
            ).findViewById(R.id.rootContainer)
        )
        binding.pointTitle.text = pointDescription
        binding.pointContainer.outlineSpotShadowColor = lightColorId
        binding.point.background = context.resources.getDrawable(pointDrawableId, context.theme)

    }

    fun select() {
        binding.pointTitle.setTextColor(lightColorId)
        binding.pointContainer.elevation = 30F
    }
}