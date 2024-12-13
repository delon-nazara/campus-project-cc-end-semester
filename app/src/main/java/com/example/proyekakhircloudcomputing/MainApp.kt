package com.example.proyekakhircloudcomputing

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
import com.example.proyekakhircloudcomputing.ui.screen.CreateCapsuleScreen
import com.example.proyekakhircloudcomputing.ui.screen.DetailCapsuleScreen
import com.example.proyekakhircloudcomputing.ui.screen.DiscoverScreen
import com.example.proyekakhircloudcomputing.ui.screen.HomeScreen
import com.example.proyekakhircloudcomputing.ui.screen.LoginScreen
import com.example.proyekakhircloudcomputing.ui.screen.NotificationScreen
import com.example.proyekakhircloudcomputing.ui.screen.RegisterScreen
import com.example.proyekakhircloudcomputing.ui.screen.SettingScreen
import com.example.proyekakhircloudcomputing.ui.screen.WelcomeScreen
import com.example.proyekakhircloudcomputing.util.showToast
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
    val addCapsuleSuccess by databaseViewModel.addCapsuleSuccess.collectAsState()
    val detailCapsule by databaseViewModel.detailCapsule.collectAsState()

    val groupedCapsules = capsulesState?.groupBy { it.type }
    val publicCapsules = groupedCapsules?.get("Public")
    val privateCapsules = groupedCapsules?.get("Private")

    val navController: NavHostController = rememberNavController()
    val navigateTo: (String, Boolean) -> Unit = { route, clearAll ->
        navController.navigate(route) {
            if (clearAll) {
                popUpTo(0) {
                    inclusive = true
                }
            } else {
                popUpTo(route) {
                    inclusive = true
                }
            }
        }
    }

    val startDestination = Route.WELCOME_SCREEN.name
    LaunchedEffect(Unit) {
        if (userAuthState != null) {
            databaseViewModel.getUserFromDatabase(
                userId = userAuthState!!.uid,
                onSuccess = {
                    databaseViewModel.getCapsulesFromDatabase()
                    navigateTo(Route.HOME_SCREEN.name, true)
                },
                onFailure = {
                    showToast(context, "Failed to get data from database")
                },
                showLoading = { state ->
                    authenticationViewModel.showLoading(state)
                }
            )
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        // Route welcome screen
        composable(Route.WELCOME_SCREEN.name) {
            WelcomeScreen(
                onLoginButtonClicked = { navigateTo(Route.LOGIN_SCREEN.name, false) },
                onRegisterButtonClicked = { navigateTo(Route.REGISTER_SCREEN.name, false) },
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
                                    navigateTo(Route.HOME_SCREEN.name, true)
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
                onRegisterButtonClicked = { navigateTo(Route.REGISTER_SCREEN.name, false) },
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
                                    navigateTo(Route.HOME_SCREEN.name, true)
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
                onLoginButtonClicked = { navigateTo(Route.LOGIN_SCREEN.name, false) },
                errorNameState = errorNameState,
                errorEmailState = errorEmailState,
                errorPasswordState = errorPasswordState,
                loadingState = loadingState
            )
        }

        // Route home screen
        composable(Route.HOME_SCREEN.name) {
            HomeScreen(
                userData = userDataState,
                privateCapsules = privateCapsules,
                publicCapsules = publicCapsules,
                navigateToDetailCapsule = { createdAt ->
                    databaseViewModel.findCapsuleInDatabase(
                        createdAt = createdAt,
                        onSuccess = {
                            navigateTo(Route.DETAIL_CAPSULE_SCREEN.name, false)
                        },
                        onFailure = {
                            showToast(context, "Failed to fetch data, try again")
                        }
                    )
                },
                navigateTo = { route ->
                    navigateTo(route, false)
                }
            )
        }

        // Route capsule screen
        composable(Route.CAPSULE_SCREEN.name) {
            CapsuleScreen(
                userData = userDataState,
                capsulesData = capsulesState,
                navigateToDetailCapsule = { createdAt ->
                    databaseViewModel.findCapsuleInDatabase(
                        createdAt = createdAt,
                        onSuccess = {
                            navigateTo(Route.DETAIL_CAPSULE_SCREEN.name, false)
                        },
                        onFailure = {
                            showToast(context, "Failed to fetch data, try again")
                        }
                    )
                },
                navigateTo = { route ->
                    navigateTo(route, false)
                }
            )
        }

        // Route discover screen
        composable(Route.DISCOVER_SCREEN.name) {
            DiscoverScreen(
                userData = userDataState,
                capsulesData = capsulesState,
                navigateToDetailCapsule = { createdAt ->
                    databaseViewModel.findCapsuleInDatabase(
                        createdAt = createdAt,
                        onSuccess = {
                            navigateTo(Route.DETAIL_CAPSULE_SCREEN.name, false)
                        },
                        onFailure = {
                            showToast(context, "Failed to fetch data, try again")
                        }
                    )
                },
                navigateTo = { route ->
                    navigateTo(route, false)
                }
            )
        }

        // Route notification screen
        composable(Route.NOTIFICATION_SCREEN.name) {
            NotificationScreen(
                userData = userDataState,
                navigateTo = { route ->
                    navigateTo(route, false)
                }
            )
        }

        // Route setting screen
        composable(Route.SETTING_SCREEN.name) {
            SettingScreen(
                userData = userDataState,
                onLogoutButtonClicked = {
                    authenticationViewModel.logout()
                    databaseViewModel.clearAllData()
                    navigateTo(Route.WELCOME_SCREEN.name, true)
                },
                navigateTo = { route ->
                    navigateTo(route, false)
                }
            )
        }

        // Route create capsule screen
        composable(Route.CREATE_CAPSULE_SCREEN.name) {
            CreateCapsuleScreen(
                popBackStack = {
                    navController.popBackStack()
                },
                loadingState = loadingState,
                addCapsuleSuccess = addCapsuleSuccess,
                navigateToDetailScreen = {
                    navController.navigate(Route.DETAIL_CAPSULE_SCREEN.name) {
                        popUpTo(Route.HOME_SCREEN.name)
                    }
                    databaseViewModel.resetAddCapsuleSuccess()
                },
                dataEmpty = {
                    showToast(context, "All data must be filled")
                },
                addNewCapsule = { indexCover, title, description, type, lockedAt, unlockedAt ->
                    databaseViewModel.addCapsuleToDatabase(
                        indexCover = indexCover,
                        title = title,
                        description = description,
                        type = type,
                        lockedAt = lockedAt,
                        unlockedAt = unlockedAt,
                        onFailure = {
                            showToast(context, "Failed to add new capsule, try again")
                        },
                        showLoading = { state ->
                            authenticationViewModel.showLoading(state)
                        }
                    )
                }
            )
        }

        // Route detail capsule screen
        composable(Route.DETAIL_CAPSULE_SCREEN.name) {
            DetailCapsuleScreen(
                popBackStack = {
                    navController.popBackStack()
                },
                capsuleData = detailCapsule!!
            )
        }
    }
}