package com.example.proyekakhircloudcomputing.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyekakhircloudcomputing.data.source.Route
import com.example.proyekakhircloudcomputing.ui.screen.HomeScreen
import com.example.proyekakhircloudcomputing.ui.screen.LoginScreen
import com.example.proyekakhircloudcomputing.ui.screen.RegisterScreen
import com.example.proyekakhircloudcomputing.ui.screen.WelcomeScreen
import com.example.proyekakhircloudcomputing.viewmodel.AuthenticationViewModel
import com.example.proyekakhircloudcomputing.viewmodel.DatabaseViewModel

@Composable
fun MainApp(context: Context) {
    val authenticationViewModel: AuthenticationViewModel = viewModel()
    val userAuthState by authenticationViewModel.userAuthState.collectAsState()
    val errorNameState by authenticationViewModel.errorNameState.collectAsState()
    val errorEmailState by authenticationViewModel.errorEmailState.collectAsState()
    val errorPasswordState by authenticationViewModel.errorPasswordState.collectAsState()

    val databaseViewModel: DatabaseViewModel = viewModel()
    databaseViewModel.cloudinaryInitialization(context)
    val userDataState by databaseViewModel.userDataState.collectAsState()

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
                onLoginButtonClicked = { email, password ->
                    if (authenticationViewModel.login(email, password)) {
                        navController.navigate(Route.HOME_SCREEN.name) {
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    }
                },
                onRegisterButtonClicked = {
                    navController.navigate(Route.REGISTER_SCREEN.name) {
                        popUpTo(Route.REGISTER_SCREEN.name) {
                            inclusive = true
                        }
                    }
                },
                errorEmailState = errorEmailState,
                errorPasswordState = errorPasswordState
            )
        }

        // Route register screen
        composable(Route.REGISTER_SCREEN.name) {
            RegisterScreen(
                onRegisterButtonClicked = { name, email, password ->
                    if (authenticationViewModel.register(name, email, password)) {
                        if (databaseViewModel.addUserToDatabase(userAuthState!!.uid, name, email)) {
                            navController.navigate(Route.HOME_SCREEN.name) {
                                popUpTo(0) {
                                    inclusive = true
                                }
                            }
                        } else {
                            showToast(context, "Failed to register, try again")
                        }
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

        // Route register screen
        composable(Route.HOME_SCREEN.name) {
            HomeScreen()
        }
    }
}