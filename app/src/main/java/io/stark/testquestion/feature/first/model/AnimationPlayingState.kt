package io.stark.testquestion.feature.first.model

sealed class AnimationPlayingState {
    object Play: AnimationPlayingState()
    object Stop: AnimationPlayingState()
}