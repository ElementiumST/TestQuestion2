package io.stark.testquestion.feature.second.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.stark.testquestion.domain.usecases.GetPersonListUseCase


class SecondViewModelFactory(
    private val getPersonListUseCase: GetPersonListUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SecondViewModel(
            getPersonListUseCase
        ) as T
    }
}