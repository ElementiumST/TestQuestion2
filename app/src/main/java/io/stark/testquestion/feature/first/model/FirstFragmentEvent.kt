package io.stark.testquestion.feature.first.model

sealed class FirstFragmentEvent {
    object StartAnimation : FirstFragmentEvent()

    object StopAnimation : FirstFragmentEvent()

    object HideAnimation : FirstFragmentEvent()

    object ShowAnimation : FirstFragmentEvent()
}