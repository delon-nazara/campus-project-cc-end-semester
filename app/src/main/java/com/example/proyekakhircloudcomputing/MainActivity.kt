package com.example.proyekakhircloudcomputing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyekakhircloudcomputing.data.source.Route
import com.example.proyekakhircloudcomputing.utils.NavigationComponent

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
    val navController: NavHostController = rememberNavController()
    val startDestination = Route.WELCOME_SCREEN.name

    NavigationComponent(
        navController = navController,
        startDestination = startDestination
    )
}