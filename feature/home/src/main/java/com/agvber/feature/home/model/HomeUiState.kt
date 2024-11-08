package com.agvber.feature.home.model

internal data class HomeUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = true
)