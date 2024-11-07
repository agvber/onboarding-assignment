package com.agvber.feature.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val registerAppUseCase: RegisterAppUseCase
) : ViewModel() {

    private val _sideEffect: Channel<SignupSideEffect> = Channel()
    val sideEffect: Flow<SignupSideEffect> = _sideEffect.receiveAsFlow()

    var uiState = mutableStateOf(SignupUiState())
        private set

    fun updateEmailState(email: String) = with(uiState) {
        value = value.copy(email = email)
    }

    fun updateNameState(name: String) = with(uiState) {
        value = value.copy(name = name)
    }

    fun updatePasswordState(password: String) = with(uiState) {
        value = value.copy(password = password)
    }

    fun registerService() = viewModelScope.launch {
        try {
            with(uiState.value) {
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