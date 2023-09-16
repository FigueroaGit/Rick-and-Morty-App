package com.figueroa.rickandmortyapp.navigation

import java.lang.IllegalArgumentException

enum class AppScreens {
    SplashScreen,
    CharactersScreen,
    DetailsCharacterScreen,
    EpisodesScreen,
    LocationsScreen
    ;
    companion object {
        fun fromRoute(route: String?): AppScreens = when (route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            CharactersScreen.name -> CharactersScreen
            DetailsCharacterScreen.name -> DetailsCharacterScreen
            EpisodesScreen.name -> EpisodesScreen
            LocationsScreen.name -> LocationsScreen
            null -> CharactersScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}
