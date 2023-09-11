package com.figueroa.rickandmortyapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.figueroa.rickandmortyapp.screens.SplashScreen
import com.figueroa.rickandmortyapp.screens.home.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.name) {
        composable(route = AppScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        composable(route = AppScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
    }
}
