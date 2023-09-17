package com.figueroa.rickandmortyapp.screens.locations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.figueroa.rickandmortyapp.model.LocationResult

@Composable
fun LocationsScreen(
    navController: NavController,
    locationsScreenViewModel: LocationsScreenViewModel = hiltViewModel(),
) {
    Box {
        LocationsList(locationsScreenViewModel)
    }
}

@Composable
fun LocationsList(locationsScreenViewModel: LocationsScreenViewModel = hiltViewModel()) {
    val listOfLocations = locationsScreenViewModel.list

    if (locationsScreenViewModel.isLoading) {
        Row(
            modifier = Modifier.padding(end = 2.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
    } else {
        LazyColumn {
            items(items = listOfLocations) { location ->
                LocationItem(locationResult = location)
            }
        }
    }
}

@Composable
fun LocationItem(locationResult: LocationResult) {
    Box {
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .background(Color.White).fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = "name: ${locationResult.name}")
                Text(text = "type: Planet ${locationResult.type}")
                Text(text = "Dimension: ${locationResult.dimension}")
            }
        }
    }
}
