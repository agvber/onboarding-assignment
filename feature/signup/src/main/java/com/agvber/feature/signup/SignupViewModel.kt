package com.agvber.feature.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.agvber.core.domain.entity.SignupEntity
import com.agvber.core.domain.usecase.RegisterAppUseCase
import com.agvber.feature.signup.model.SignupSideEffect
import com.agvber.feature.signup.model.SignupUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SignupViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val registerAppUseCase: RegisterAppUseCase
) : ViewModel() {

    private val _sideEffect: Channel<SignupSideEffect> = Channel()
    val sideEffect: Flow<SignupSideEffect> = _sideEffect.receiveAsFlow()

    @OptIn(SavedStateHandleSaveableApi::class)
    var uiState by savedStateHandle.saveable { mutableStateOf(SignupUiState()) }
        private set

    fun updateEmailState(email: String) {
        uiState = uiState.copy(email = email)
    }

    fun updateNameState(name: String) {
        uiState = uiState.copy(name = name)
    }

    fun updatePasswordState(password: String) {
        uiState = uiState.copy(password = password)
    }

    fun registerService() = viewModelScope.launch {
        try {
            with(uiState) {
                registerAppUseCase(email = email, name = name, password = password)
                _sideEffect.send(SignupSideEffect.SignupSuccess)
            }
        } catch (e: Exception) {
            val error = when (e.message) {
                SignupEntity.EMAIL_INVALID_MESSAGE -> SignupSideEffect.EmailFormatError
                SignupEntity.NAME_INVALID_MESSAGE -> SignupSideEffect.NameFormatError
                SignupEntity.PASSWORD_INVALID_MESSAGE -> SignupSideEffect.PasswordFormatError
                else -> SignupSideEffect.SignupFail
            }
            _sideEffect.send(error)
            e.printStackTrace()
        }
    }
}