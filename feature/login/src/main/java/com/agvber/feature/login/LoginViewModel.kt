package com.agvber.feature.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.agvber.core.domain.usecase.LoginAppUseCase
import com.agvber.feature.login.model.LoginSideEffect
import com.agvber.feature.login.model.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val loginAppUseCase: LoginAppUseCase
) : ViewModel() {

    private val _sideEffect: Channel<LoginSideEffect> = Channel()
    val sideEffect: Flow<LoginSideEffect> = _sideEffect.receiveAsFlow()

    @OptIn(SavedStateHandleSaveableApi::class)
    var uiState: LoginUiState by savedStateHandle.saveable {
        mutableStateOf(LoginUiState())
    }
        private set

    fun updateEmailState(email: String) {
        uiState = uiState.copy(email = email)
    }

    fun updatePasswordState(password: String) {
        uiState = uiState.copy(password = password)
    }

    fun login() = viewModelScope.launch {
        try {
            val user = loginAppUseCase(uiState.email, uiState.password)
            _sideEffect.send(LoginSideEffect.LoginSuccess(user.uid))
        } catch (e: Exception) {
            _sideEffect.send(LoginSideEffect.LoginFail)
            e.printStackTrace()
        }
    }
}