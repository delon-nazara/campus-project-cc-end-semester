package com.example.proyekakhircloudcomputing.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyekakhircloudcomputing.data.source.Route
import com.example.proyekakhircloudcomputing.ui.screen.LoginScreen
import com.example.proyekakhircloudcomputing.ui.screen.RegisterScreen
import com.example.proyekakhircloudcomputing.ui.screen.WelcomeScreen
import com.example.proyekakhircloudcomputing.viewmodel.AuthViewModel

@Composable
fun MainApp() {
    val authViewModel: AuthViewModel = viewModel()
    val userState by authViewModel.userState.collectAsState()
    val errorNameState by authViewModel.errorNameState.collectAsState()
    val errorEmailState by authViewModel.errorEmailState.collectAsState()
    val errorPasswordState by authViewModel.errorPasswordState.collectAsState()

    val navController: NavHostController = rememberNavController()
    val startDestination = Route.WELCOME_SCREEN.name

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
                onRegisterButtonClicked = { name, email, password ->
                    if (authViewModel.register(name, email, password)) {
                        // direct to home screen
                    }
                },
                onLoginButtonClicked = {
                    navController.navigate(Route.LOGIN_SCREEN.name) {
                        popUpTo(Route.LOGIN_SCREEN.name) {
                            inclusive = true
                        }
                    }
                },
                errorNameState = errorNameState,
                errorEmailState = errorEmailState,
                errorPasswordState = errorPasswordState
            )
        }
    }
}