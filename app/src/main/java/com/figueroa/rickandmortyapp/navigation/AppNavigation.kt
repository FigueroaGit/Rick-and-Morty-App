package com.figueroa.rickandmortyapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.figueroa.rickandmortyapp.screens.SplashScreen
import com.figueroa.rickandmortyapp.screens.characters.CharactersScreen
import com.figueroa.rickandmortyapp.screens.characters.CharactersScreenViewModel
import com.figueroa.rickandmortyapp.screens.characters.DetailsCharacterScreen
import com.figueroa.rickandmortyapp.screens.episodes.EpisodesScreen
import com.figueroa.rickandmortyapp.screens.episodes.EpisodesScreenViewModel
import com.figueroa.rickandmortyapp.screens.locations.LocationsScreen

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
        composable(
            route = AppScreens.DetailsCharacterScreen.name + "/{characterId}",
            arguments = listOf(
                navArgument("characterId") {
                    type = NavType.StringType
                },
            ),
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("characterId").let {
                DetailsCharacterScreen(navController = navController, characterId = it.toString())
            }
        }
        composable(route = AppScreens.EpisodesScreen.name) {
            val episodesScreenViewModel = hiltViewModel<EpisodesScreenViewModel>()
            EpisodesScreen(navController = navController, episodesScreenViewModel)
        }
        composable(route = AppScreens.LocationsScreen.name) {
            LocationsScreen(navController = navController)
        }
    }
}
