package com.example.proyekakhircloudcomputing.utils

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyekakhircloudcomputing.data.source.Route
import com.example.proyekakhircloudcomputing.ui.screen.CapsuleScreen
import com.example.proyekakhircloudcomputing.ui.screen.DiscoverScreen
import com.example.proyekakhircloudcomputing.ui.screen.HomeScreen
import com.example.proyekakhircloudcomputing.ui.screen.LoginScreen
import com.example.proyekakhircloudcomputing.ui.screen.NotificationScreen
import com.example.proyekakhircloudcomputing.ui.screen.RegisterScreen
import com.example.proyekakhircloudcomputing.ui.screen.SettingScreen
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
    val errorAllState by authenticationViewModel.errorAllState.collectAsState()
    val loadingState by authenticationViewModel.loadingState.collectAsState()

    val databaseViewModel: DatabaseViewModel = viewModel()
    databaseViewModel.cloudinaryInitialization(context)
    val userDataState by databaseViewModel.userDataState.collectAsState()
    val capsulesState by databaseViewModel.capsulesState.collectAsState()

    val navController: NavHostController = rememberNavController()

    val startDestination = Route.WELCOME_SCREEN.name
    if (userAuthState != null) {
        databaseViewModel.getUserFromDatabase(
            userId = userAuthState!!.uid,
            onSuccess = {
                databaseViewModel.getCapsulesFromDatabase()
                navController.navigate(Route.HOME_SCREEN.name) {
                    popUpTo(0) {
                        inclusive = true
                    }
                }
            },
            onFailure = {
                showToast(context, "Failed to get data from database")
            },
            showLoading = { state ->
                authenticationViewModel.showLoading(state)
            }
        )
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        val navigateTo: (String) -> Unit = { route ->
            navController.navigate(route) {
                popUpTo(route) {
                    inclusive = true
                }
            }
        }

        // Route welcome screen
        composable(Route.WELCOME_SCREEN.name) {
            WelcomeScreen(
                onLoginButtonClicked = { navigateTo(Route.LOGIN_SCREEN.name) },
                onRegisterButtonClicked = { navigateTo(Route.REGISTER_SCREEN.name) },
                loadingState = loadingState
            )
        }

        // Route login screen
        composable(Route.LOGIN_SCREEN.name) {
            LoginScreen(
                onLoginButtonClicked = { email, password ->
                    authenticationViewModel.login(
                        email = email,
                        password = password,
                        onSuccess = { userId ->
                            databaseViewModel.getUserFromDatabase(
                                userId = userId,
                                onSuccess = {
                                    databaseViewModel.getCapsulesFromDatabase()
                                    navController.navigate(Route.HOME_SCREEN.name) {
                                        popUpTo(0) {
                                            inclusive = true
                                        }
                                    }
                                },
                                onFailure = {
                                    showToast(context, "Failed to login, try again")
                                },
                                showLoading = { state ->
                                    authenticationViewModel.showLoading(state)
                                }
                            )
                        },
                        onFailure = {
                            showToast(context, "Failed to login, try again")
                        }
                    )
                },
                onRegisterButtonClicked = { navigateTo(Route.REGISTER_SCREEN.name) },
                errorEmailState = errorEmailState,
                errorPasswordState = errorPasswordState,
                errorAllState = errorAllState,
                loadingState = loadingState
            )
        }

        // Route register screen
        composable(Route.REGISTER_SCREEN.name) {
            RegisterScreen(
                onRegisterButtonClicked = { name, email, password ->
                    authenticationViewModel.register(
                        name = name,
                        email = email,
                        password = password,
                        onSuccess = { userAuth ->
                            databaseViewModel.addUserToDatabase(
                                userAuth = userAuth,
                                name = name,
                                email = email,
                                onSuccess = {
                                    databaseViewModel.getCapsulesFromDatabase()
                                    navController.navigate(Route.HOME_SCREEN.name) {
                                        popUpTo(0) {
                                            inclusive = true
                                        }
                                    }
                                },
                                onFailure = {
                                    showToast(context, "Failed to register, try again")
                                },
                                showLoading = { state ->
                                    authenticationViewModel.showLoading(state)
                                }
                            )
                        },
                        onFailure = {
                            showToast(context, "Failed to register, try again")
                        }
                    )
                },
                onLoginButtonClicked = { navigateTo(Route.LOGIN_SCREEN.name) },
                errorNameState = errorNameState,
                errorEmailState = errorEmailState,
                errorPasswordState = errorPasswordState,
                loadingState = loadingState
            )
        }

        // Route home screen
        composable(route = Route.HOME_SCREEN.name) {
            HomeScreen(
                userData = userDataState,
                capsulesData = capsulesState,
                onUserProfileClicked = { navigateTo(Route.SETTING_SCREEN.name) },
                onNotificationIconClicked = { navigateTo(Route.NOTIFICATION_SCREEN.name) },
                onHomeButtonClicked = { navigateTo(Route.HOME_SCREEN.name) },
                onCapsuleButtonClicked = { navigateTo(Route.CAPSULE_SCREEN.name) },
                onDiscoverButtonClicked = { navigateTo(Route.DISCOVER_SCREEN.name) },
                onNotificationButtonClicked = { navigateTo(Route.NOTIFICATION_SCREEN.name) },
                onSettingButtonClicked = { navigateTo(Route.SETTING_SCREEN.name) }
            )
        }

        // Route capsule screen
        composable(route = Route.CAPSULE_SCREEN.name) {
            CapsuleScreen(
                userData = userDataState,
                capsulesData = capsulesState,
                onUserProfileClicked = { navigateTo(Route.SETTING_SCREEN.name) },
                onNotificationIconClicked = { navigateTo(Route.NOTIFICATION_SCREEN.name) },
                onHomeButtonClicked = { navigateTo(Route.HOME_SCREEN.name) },
                onCapsuleButtonClicked = { navigateTo(Route.CAPSULE_SCREEN.name) },
                onDiscoverButtonClicked = { navigateTo(Route.DISCOVER_SCREEN.name) },
                onNotificationButtonClicked = { navigateTo(Route.NOTIFICATION_SCREEN.name) },
                onSettingButtonClicked = { navigateTo(Route.SETTING_SCREEN.name) }
            )
        }

        // Route discover screen
        composable(route = Route.DISCOVER_SCREEN.name) {
            DiscoverScreen(
                userData = userDataState,
                capsulesData = capsulesState,
                onUserProfileClicked = { navigateTo(Route.SETTING_SCREEN.name) },
                onNotificationIconClicked = { navigateTo(Route.NOTIFICATION_SCREEN.name) },
                onHomeButtonClicked = { navigateTo(Route.HOME_SCREEN.name) },
                onCapsuleButtonClicked = { navigateTo(Route.CAPSULE_SCREEN.name) },
                onDiscoverButtonClicked = { navigateTo(Route.DISCOVER_SCREEN.name) },
                onNotificationButtonClicked = { navigateTo(Route.NOTIFICATION_SCREEN.name) },
                onSettingButtonClicked = { navigateTo(Route.SETTING_SCREEN.name) }
            )
        }

        // Route notification screen
        composable(route = Route.NOTIFICATION_SCREEN.name) {
            NotificationScreen(
                userData = userDataState,
                onUserProfileClicked = { navigateTo(Route.SETTING_SCREEN.name) },
                onNotificationIconClicked = { navigateTo(Route.NOTIFICATION_SCREEN.name) },
                onHomeButtonClicked = { navigateTo(Route.HOME_SCREEN.name) },
                onCapsuleButtonClicked = { navigateTo(Route.CAPSULE_SCREEN.name) },
                onDiscoverButtonClicked = { navigateTo(Route.DISCOVER_SCREEN.name) },
                onNotificationButtonClicked = { navigateTo(Route.NOTIFICATION_SCREEN.name) },
                onSettingButtonClicked = { navigateTo(Route.SETTING_SCREEN.name) }
            )
        }

        // Route setting screen
        composable(route = Route.SETTING_SCREEN.name) {
            SettingScreen(
                userData = userDataState,
                onUserProfileClicked = { navigateTo(Route.SETTING_SCREEN.name) },
                onNotificationIconClicked = { navigateTo(Route.NOTIFICATION_SCREEN.name) },
                onHomeButtonClicked = { navigateTo(Route.HOME_SCREEN.name) },
                onCapsuleButtonClicked = { navigateTo(Route.CAPSULE_SCREEN.name) },
                onDiscoverButtonClicked = { navigateTo(Route.DISCOVER_SCREEN.name) },
                onNotificationButtonClicked = { navigateTo(Route.NOTIFICATION_SCREEN.name) },
                onSettingButtonClicked = { navigateTo(Route.SETTING_SCREEN.name) },
                onLogoutButtonClicked = {
                    authenticationViewModel.logout()
                    databaseViewModel.clearAllData()
                    navController.navigate(Route.WELCOME_SCREEN.name) {
                        popUpTo(0) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}