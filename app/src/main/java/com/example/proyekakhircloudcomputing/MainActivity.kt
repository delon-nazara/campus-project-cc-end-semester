package com.example.proyekakhircloudcomputing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyekakhircloudcomputing.data.source.Route
import com.example.proyekakhircloudcomputing.utils.NavigationComponent
import com.example.proyekakhircloudcomputing.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp()
        }
    }
}

@Composable
fun MainApp() {
    val authViewModel: AuthViewModel = viewModel()
    val userState by authViewModel.userState.collectAsState()

    val navController: NavHostController = rememberNavController()
    val startDestination = Route.WELCOME_SCREEN.name

    NavigationComponent(
        navController = navController,
        startDestination = startDestination,
        authViewModel = authViewModel
    )
}