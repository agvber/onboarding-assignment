package com.agvber.feature.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.agvber.core.domain.usecase.GetUserInformationUseCase
import com.agvber.feature.home.model.HomeSideEffect
import com.agvber.feature.home.model.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserInformationUseCase: GetUserInformationUseCase
) : ViewModel() {

    private val homeRoute = kotlin.runCatching { savedStateHandle.toRoute<HomeRoute>() }
        .getOrNull() ?: HomeRoute("")

    var uiState by mutableStateOf(HomeUiState())
        private set

    private val _sideEffect: Channel<HomeSideEffect> = Channel()
    val sideEffect: Flow<HomeSideEffect> = _sideEffect.receiveAsFlow()

    init {
        load()
    }

    private fun load() = viewModelScope.launch {
        try {
            val user = getUserInformationUseCase(homeRoute.uid)
            uiState = uiState.copy(
                name = user.name,
                email = user.email,
                password = user.password,
                isLoading = false
            )
        } catch (e: Exception) {
            _sideEffect.send(HomeSideEffect.LoadError)
            e.printStackTrace()
        }
    }
}