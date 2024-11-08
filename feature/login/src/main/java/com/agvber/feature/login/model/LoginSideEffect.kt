package com.agvber.feature.login.model

internal sealed interface LoginSideEffect {

    data class LoginSuccess(val uid: String) : LoginSideEffect
    data object LoginFail : LoginSideEffect
}