package com.figueroa.rickandmortyapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.figueroa.rickandmortyapp.screens.SplashScreen
import com.figueroa.rickandmortyapp.screens.characters.CharactersScreen
import com.figueroa.rickandmortyapp.screens.characters.CharactersScreenViewModel
import com.figueroa.rickandmortyapp.screens.characters.DetailsCharacterScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.name) {
        composable(route = AppScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        composable(route = AppScreens.CharactersScreen.name) {
            val homeScreenViewModel = hiltViewModel<CharactersScreenViewModel>()
            CharactersScreen(navController = navController, homeScreenViewModel)
        }
        composable(route = AppScreens.DetailsCharacterScreen.name) {
            DetailsCharacterScreen(navController = navController)
        }
    }
}
