package com.agvber.feature.home.model

internal sealed interface HomeSideEffect {

    data object LoadError : HomeSideEffect
}