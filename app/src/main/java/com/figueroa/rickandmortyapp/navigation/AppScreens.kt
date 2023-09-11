package com.figueroa.rickandmortyapp.navigation

import java.lang.IllegalArgumentException

enum class AppScreens {
    SplashScreen,
    HomeScreen, ;
    companion object {
        fun fromRoute(route: String?): AppScreens = when (route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            HomeScreen.name -> HomeScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}
