package io.stark.testquestion.feature.dimension

import android.content.res.Resources
import android.util.TypedValue

inline val Number.toPx get() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    Resources.getSystem().displayMetrics)