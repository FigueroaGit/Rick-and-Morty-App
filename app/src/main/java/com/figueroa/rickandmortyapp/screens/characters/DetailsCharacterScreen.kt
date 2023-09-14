package com.figueroa.rickandmortyapp.screens.characters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.figueroa.rickandmortyapp.navigation.AppScreens
import com.figueroa.rickandmortyapp.widgets.CornerCardCut

@Composable
fun DetailsCharacterScreen(navController: NavController) {
    Box {
        Card(
            modifier = Modifier.size(72.dp).padding(8.dp),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomStart = 0.dp,
                bottomEnd = 64.dp,
            ),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(10.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
                    .clickable { navController.navigate(AppScreens.CharactersScreen.name) },
                contentAlignment = Alignment.Center,
            ) {
                Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "Arrow Back")
            }
        }
        CharacterCardDetails()
    }
}

@Composable
fun CharacterCardDetails() {
    Card(
        modifier = Modifier
            .fillMaxSize().padding(8.dp),
        shape = CornerCardCut(172F),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
    }
}
