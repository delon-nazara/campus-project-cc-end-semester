package com.example.proyekakhircloudcomputing.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.proyekakhircloudcomputing.data.source.Route
import com.example.proyekakhircloudcomputing.ui.screen.LoginScreen
import com.example.proyekakhircloudcomputing.ui.screen.WelcomeScreen

@Composable
fun NavigationComponent(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Route welcome screen
        composable(Route.WELCOME_SCREEN.name) {
            WelcomeScreen(
                onLoginButtonClicked = {
                    navController.navigate(Route.LOGIN_SCREEN.name)
                },
                onRegisterButtonClicked = {
                    navController.navigate(Route.REGISTER_SCREEN.name)
                }
            )
        }

        // Route login screen
        composable(Route.LOGIN_SCREEN.name) {
            LoginScreen(
                onRegisterButtonClicked = {
                    navController.navigate(Route.REGISTER_SCREEN.name)
                }
            )
        }

        // Route register screen
        composable(Route.REGISTER_SCREEN.name) {

        }
    }
}