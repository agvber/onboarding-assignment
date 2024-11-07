package com.agvber.onboarding

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.agvber.feature.signup.SignupRoute

@Composable
fun OnboardingNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController, startDestination = SignupRoute) {

        composable<SignupRoute> {
            SignupRoute(
                onBackRequest = navController::popBackStack,
                onCompleteRequest = {},
                loginPageRequest = {}
            )
        }
    }
}