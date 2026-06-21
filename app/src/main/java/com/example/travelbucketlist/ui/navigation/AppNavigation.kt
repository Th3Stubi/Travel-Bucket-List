package com.example.travelbucketlist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travelbucketlist.ui.features.auth.LoginScreen
import com.example.travelbucketlist.ui.features.auth.RegisterScreen
import com.example.travelbucketlist.ui.features.main.MainScreen
import com.example.travelbucketlist.ui.features.settings.SettingsScreen

/**
 * The central navigation graph of the application.
 * It controls which screen is visible based on the current route.
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

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
                onSettingsClick = { navController.navigate("settings") }
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