package com.example.proyekakhircloudcomputing.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.proyekakhircloudcomputing.data.source.Route
import com.example.proyekakhircloudcomputing.ui.screen.LoginScreen
import com.example.proyekakhircloudcomputing.ui.screen.RegisterScreen
import com.example.proyekakhircloudcomputing.ui.screen.WelcomeScreen
import com.example.proyekakhircloudcomputing.viewmodel.AuthViewModel

@Composable
fun NavigationComponent(
    navController: NavHostController,
    startDestination: String,
    authViewModel: AuthViewModel
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        // Route welcome screen
        composable(Route.WELCOME_SCREEN.name) {
            WelcomeScreen(
                onLoginButtonClicked = {
                    navController.navigate(Route.LOGIN_SCREEN.name) {
                        popUpTo(Route.LOGIN_SCREEN.name) {
                            inclusive = true
                        }
                    }
                },
                onRegisterButtonClicked = {
                    navController.navigate(Route.REGISTER_SCREEN.name) {
                        popUpTo(Route.REGISTER_SCREEN.name) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Route login screen
        composable(Route.LOGIN_SCREEN.name) {
            LoginScreen(
                onRegisterButtonClicked = {
                    navController.navigate(Route.REGISTER_SCREEN.name) {
                        popUpTo(Route.REGISTER_SCREEN.name) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Route register screen
        composable(Route.REGISTER_SCREEN.name) {
            RegisterScreen(
                onRegisterButtonClicked = { fullName, email, password ->
                    authViewModel.register(email, password)
                },
                onLoginButtonClicked = {
                    navController.navigate(Route.LOGIN_SCREEN.name) {
                        popUpTo(Route.LOGIN_SCREEN.name) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}