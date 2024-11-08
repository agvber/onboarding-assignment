package com.agvber.feature.signup.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignupUiState(
    val email: String = "",
    val name: String = "",
    val password: String = ""
) : Parcelable