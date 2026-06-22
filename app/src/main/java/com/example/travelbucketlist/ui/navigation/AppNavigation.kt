package com.example.travelbucketlist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travelbucketlist.ui.features.auth.LoginScreen
import com.example.travelbucketlist.ui.features.auth.RegisterScreen
import com.example.travelbucketlist.ui.features.bucketlist.AddLocationScreen
import com.example.travelbucketlist.ui.features.bucketlist.BucketListViewModel
import com.example.travelbucketlist.ui.features.bucketlist.DestinationDetailScreen
import com.example.travelbucketlist.ui.features.main.MainScreen
import com.example.travelbucketlist.ui.features.settings.SettingsScreen

/**
 * The central navigation graph of the application.
 * It controls which screen is visible based on the current route.
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val bucketListViewModel: BucketListViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "register"
    ) {
        composable(route = "login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = { navController.navigate("register") }
            )
        }

        composable(route = "register") {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate("main") {
                        popUpTo("register") { inclusive = true }
                    }
                },
                onNavigateToLogin = { navController.navigate("login") }
            )
        }

        composable(route = "main") {
            MainScreen(
                onSettingsClick = { navController.navigate("settings") },
                onAddLocationClick = { navController.navigate("addLocation") },
                onDestinationClick = { destination ->
                    bucketListViewModel.selectDestination(destination)
                    navController.navigate("detail")},
                viewModel = bucketListViewModel
            )
        }

        composable(route = "addLocation") {
            AddLocationScreen(
                viewModel = bucketListViewModel,
                onBackClick = {
                    navController.navigate("main") {
                        popUpTo("addLocation") { inclusive = true }
                    }
                }
            )
        }
        composable(route = "detail") {
            val context = androidx.compose.ui.platform.LocalContext.current
            DestinationDetailScreen(
                viewModel = bucketListViewModel,
                onBackClick = {
                    android.widget.Toast.makeText(context, "BACK CLICKED", android.widget.Toast.LENGTH_SHORT).show()
                    navController.popBackStack("main", inclusive = false)
                }
            )
        }

        composable(route = "settings") {
            SettingsScreen(
                onLogout = {
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onBackClick = {
                    navController.navigate("main") {
                        popUpTo("settings") { inclusive = true }
                    }
                }
            )
        }
    }
}