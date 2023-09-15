package com.figueroa.rickandmortyapp.screens.characters

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.figueroa.rickandmortyapp.R
import com.figueroa.rickandmortyapp.data.Resource
import com.figueroa.rickandmortyapp.model.CharacterResult
import com.figueroa.rickandmortyapp.navigation.AppScreens
import com.figueroa.rickandmortyapp.widgets.CornerCardCut

@Composable
fun DetailsCharacterScreen(
    navController: NavController,
    characterId: String,
    viewModel: DetailsScreenViewModel = hiltViewModel(),
) {
    val singleCharacter = produceState<Resource<CharacterResult>>(initialValue = Resource.Loading()) {
        value = viewModel.getSingleCharacter(characterId)
    }.value
    Box {
        Card(
            modifier = Modifier
                .size(72.dp)
                .padding(8.dp),
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
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { navController.navigate(AppScreens.CharactersScreen.name) },
                contentAlignment = Alignment.Center,
            ) {
                Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "Arrow Back")
            }
        }
        CharacterCardDetails(singleCharacter)
    }
}

@Composable
fun CharacterCardDetails(singleCharacter: Resource<CharacterResult>) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        shape = CornerCardCut(172F),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Box {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(singleCharacter.data?.image).build(),
                contentDescription = "Character Image",
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
            ) {
                Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = singleCharacter.data?.name.toString(),
                        fontWeight = FontWeight.Black,
                        style = MaterialTheme.typography.headlineMedium,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(painter = painterResource(id = R.drawable.male_gender), contentDescription = "Gender Icon")
                }
                Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Rounded.LocationOn, contentDescription = "Gender Icon")
                    Text(
                        text = singleCharacter.data?.location?.name.toString(),
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
        Column(Modifier.padding(16.dp)) {
            Text(
                text = "Status",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(8.dp),
            )
            Row(
                modifier = Modifier.border(
                    border = BorderStroke(1.dp, Color.LightGray),
                    shape = RoundedCornerShape(24.dp),
                ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Surface(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(8.dp),
                    shape = RoundedCornerShape(8.dp),
                    color = Color.Green,
                    content = {},
                )
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = singleCharacter.data?.status.toString(),
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
        Divider(Modifier.padding(top = 8.dp))
        Column(Modifier.padding(16.dp)) {
            Text(
                text = "Specie",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(8.dp),
            )
            Row(
                modifier = Modifier.border(
                    border = BorderStroke(1.dp, Color.LightGray),
                    shape = RoundedCornerShape(24.dp),
                ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = singleCharacter.data?.species.toString(),
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
        Divider(Modifier.padding(top = 8.dp))

        Column(Modifier.padding(16.dp)) {
            Text(
                text = "Origin",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(8.dp),
            )
            Row(
                modifier = Modifier.border(
                    border = BorderStroke(1.dp, Color.LightGray),
                    shape = RoundedCornerShape(24.dp),
                ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = singleCharacter.data?.origin?.name.toString(),
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}
