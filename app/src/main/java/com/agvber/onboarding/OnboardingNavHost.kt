package com.agvber.onboarding

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.agvber.feature.login.LoginRoute
import com.agvber.feature.signup.SignupRoute

@Composable
fun OnboardingNavHost(
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current

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
                onSuccessRequest = {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                },
                registerPageRequest = { navController.navigate(SignupRoute) }
            )
        }
    }
}