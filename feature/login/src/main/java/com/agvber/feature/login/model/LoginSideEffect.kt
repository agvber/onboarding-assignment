package com.agvber.feature.login.model

internal sealed interface LoginSideEffect {

    data object LoginSuccess : LoginSideEffect
    data object LoginFail : LoginSideEffect
}