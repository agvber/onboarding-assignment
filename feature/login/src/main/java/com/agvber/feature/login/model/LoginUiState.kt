package com.agvber.feature.login.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginUiState(
    val email: String = "",
    val password: String = ""
): Parcelable
