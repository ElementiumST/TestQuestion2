package io.stark.testquestion.feature.first.model

sealed class AnimationShowState {
    object Show: AnimationShowState()
    object Hide: AnimationShowState()
}