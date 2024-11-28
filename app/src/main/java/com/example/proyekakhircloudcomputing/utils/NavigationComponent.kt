package com.example.proyekakhircloudcomputing.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.proyekakhircloudcomputing.data.source.Route

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
        composable(
            route = Route.WELCOME_SCREEN.name
        ) {

        }
    }
}