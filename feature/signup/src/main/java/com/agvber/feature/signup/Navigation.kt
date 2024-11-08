package com.agvber.feature.signup

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object SignupRoute

fun NavGraphBuilder.addSignupScreen(
    onBackRequest: () -> Unit,
    onCompleteRequest: () -> Unit,
    loginPageRequest: () -> Unit,
) {
    composable<SignupRoute> {
        SignupRoute(
            onBackRequest = onBackRequest,
            onCompleteRequest = onCompleteRequest,
            loginPageRequest = loginPageRequest
        )
    }
}