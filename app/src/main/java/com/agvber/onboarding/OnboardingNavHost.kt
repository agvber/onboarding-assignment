package com.agvber.onboarding

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.agvber.feature.home.HomeRoute
import com.agvber.feature.login.LoginRoute
import com.agvber.feature.signup.SignupRoute

@Composable
fun OnboardingNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController, startDestination = SignupRoute) {

        composable<SignupRoute> {
            SignupRoute(
                onBackRequest = navController::popBackStack,
                onCompleteRequest = { navController.navigate(LoginRoute) },
                loginPageRequest = { navController.navigate(LoginRoute) }
            )
        }

        composable<LoginRoute> {
            LoginRoute(
                onBackRequest = navController::popBackStack,
                onSuccessRequest = { navController.navigate(HomeRoute(it)) },
                registerPageRequest = { navController.navigate(SignupRoute) }
            )
        }

        composable<HomeRoute> {
            HomeRoute()
        }
    }
}