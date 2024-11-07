package com.agvber.onboarding

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun OnboardingNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController, startDestination = "signup") {

    }
}