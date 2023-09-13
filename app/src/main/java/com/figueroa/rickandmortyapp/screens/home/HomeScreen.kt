package com.figueroa.rickandmortyapp.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.figueroa.rickandmortyapp.components.RickAndMortyAppBar
import com.figueroa.rickandmortyapp.model.CharacterResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            RickAndMortyAppBar(title = "Characters", isHome = true, navController = navController)
        },
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            // val listCharacters = homeScreenViewModel.list
            // Text(text = listCharacters.toString())
            CharacterList(navController = navController, homeScreenViewModel)
        }
    }
}

@Composable
fun CharacterList(
    navController: NavController,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel(),
) {
    val listOfCharacters = homeScreenViewModel.list
    if (homeScreenViewModel.isLoading) {
        Row(
            modifier = Modifier.padding(end = 2.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LinearProgressIndicator()
            Text(text = "Loading...")
        }
    } else {
        LazyVerticalGrid(modifier = Modifier.fillMaxSize(), columns = GridCells.Fixed(2)) {
            items(items = listOfCharacters) { character ->
                CharacterItem(characterResult = character, navController = navController)
            }
        }
    }
}

@Composable
fun CharacterItem(
    characterResult: CharacterResult,
    navController: NavController,
) {
    Card(
        modifier = Modifier.padding(8.dp).clickable { },
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(characterResult.image)
                    .build(),
                contentDescription = "Character Image",
                modifier = Modifier.align(Alignment.CenterHorizontally).width(200.dp)
                    .height(200.dp).clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop,
            )
            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = characterResult.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = characterResult.gender,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp,
                )
            }
        }
    }
}