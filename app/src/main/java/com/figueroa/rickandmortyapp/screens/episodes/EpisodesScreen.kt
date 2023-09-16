package com.figueroa.rickandmortyapp.screens.episodes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.figueroa.rickandmortyapp.model.EpisodeResult

@Composable
fun EpisodesScreen(
    navController: NavController,
    episodesScreenViewModel: EpisodesScreenViewModel = hiltViewModel(),
) {
    Box {
        EpisodesList(episodesScreenViewModel)
    }
}

@Composable
fun EpisodesList(episodesScreenViewModel: EpisodesScreenViewModel = hiltViewModel()) {
    val listOfEpisodes = episodesScreenViewModel.list

    if (episodesScreenViewModel.isLoading) {
        Row(
            modifier = Modifier.padding(end = 2.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
    } else {
        LazyColumn {
            items(items = listOfEpisodes) { episode ->
                EpisodeItem(episodeResult = episode)
            }
        }
    }
}

@Composable
fun EpisodeItem(
    episodeResult: EpisodeResult,
) {
    Box(modifier = Modifier.clickable { }) {
        Column(modifier = Modifier.background(Color.White)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        modifier = Modifier.width(320.dp),
                        text = "${
                            episodeResult.episode.substring(0, 3)
                        } ${episodeResult.episode.substring(3, 6)} ∙ ${episodeResult.name}",
                        style = MaterialTheme.typography.bodyLarge,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Text(
                        text = episodeResult.air_date,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                    )
                }
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Rounded.ChevronRight,
                        contentDescription = "Arrow Next",
                        tint = Color.DarkGray,
                    )
                }
            }
            Divider()
        }
    }
}
