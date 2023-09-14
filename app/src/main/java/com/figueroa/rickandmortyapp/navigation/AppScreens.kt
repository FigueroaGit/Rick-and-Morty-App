package com.figueroa.rickandmortyapp.navigation

import java.lang.IllegalArgumentException

enum class AppScreens {
    SplashScreen,
    CharactersScreen,
    DetailsCharacterScreen;
    companion object {
        fun fromRoute(route: String?): AppScreens = when (route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            CharactersScreen.name -> CharactersScreen
            DetailsCharacterScreen.name -> DetailsCharacterScreen
            null -> CharactersScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}
