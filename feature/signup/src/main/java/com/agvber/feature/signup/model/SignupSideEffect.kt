package com.agvber.feature.signup.model

internal sealed interface SignupSideEffect {

    data object SignupSuccess : SignupSideEffect
    data object SignupFail : SignupSideEffect
    data object EmailFormatError : SignupSideEffect
    data object NameFormatError : SignupSideEffect
    data object PasswordFormatError : SignupSideEffect
}