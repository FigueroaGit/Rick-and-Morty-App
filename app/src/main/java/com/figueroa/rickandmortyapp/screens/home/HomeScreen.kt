package com.figueroa.rickandmortyapp.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.figueroa.rickandmortyapp.components.RickAndMortyAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, homeScreenViewModel: HomeScreenViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            RickAndMortyAppBar(title = "Characters", isHome = true, navController = navController)
        },
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            val listCharacters = homeScreenViewModel.list
            Text(text = listCharacters.toString())
        }
    }
}
