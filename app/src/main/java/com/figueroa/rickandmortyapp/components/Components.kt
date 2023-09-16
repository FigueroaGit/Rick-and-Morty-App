package com.figueroa.rickandmortyapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.figueroa.rickandmortyapp.navigation.AppScreens
import com.figueroa.rickandmortyapp.widgets.CornerCardCut
import com.figueroa.rickandmortyapp.widgets.showToast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RickAndMortyAppBar(
    title: String,
    isHome: Boolean = true,
    icon: ImageVector? = null,
    navController: NavController,
    onBackPressed: () -> Unit = {},
) {
    val showDialog = remember {
        mutableStateOf(false)
    }
    if (showDialog.value) {
        ShowSettingDropDownMenu(showDialog = showDialog, navController = navController)
    }
    val context = LocalContext.current
    Surface(shadowElevation = 8.dp) {
        TopAppBar(
            title = {
                Text(text = title)
            },
            navigationIcon = {
                if (!isHome) {
                    IconButton(onClick = { onBackPressed.invoke() }) {
                        if (icon != null) {
                            Icon(imageVector = icon, contentDescription = "Arrow Back")
                        }
                    }
                } else {
                    Box {}
                }
            },
            actions = {
                if (isHome) {
                    IconButton(onClick = {
                        showToast(
                            context = context,
                            message = "Coming soon",
                        )
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Search action",
                        )
                    }
                    IconButton(onClick = {
                        showDialog.value = true
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.MoreVert,
                            contentDescription = "More actions",
                        )
                    }
                } else {
                    Box {}
                }
            },
        )
    }
}

@Composable
fun RickAndMortyBottomNavigationBar(navController: NavController) {
    val screens = listOf(
        "Characters",
        "Episodes",
        "Locations",
    )
    var selectedScreen by remember { mutableStateOf(screens.first()) }
    Surface(shadowElevation = 8.dp) {
        NavigationBar {
            screens.forEach { screen ->
                NavigationBarItem(
                    icon = { Icon(getIconForScreen(screen), contentDescription = screen) },
                    label = { Text(screen) },
                    selected = screen == selectedScreen,
                    onClick = {
                        navController.navigate(
                            when (screen) {
                                "Characters" -> AppScreens.CharactersScreen.name
                                else -> {
                                    ""
                                }
                            },
                        )
                    },
                    modifier = Modifier.padding(8.dp),
                )
            }
        }
    }
}

@Composable
fun getIconForScreen(screen: String): ImageVector {
    return when (screen) {
        "Characters" -> Icons.Rounded.Person
        "Episodes" -> Icons.Rounded.Movie
        "Locations" -> Icons.Rounded.LocationOn
        else -> Icons.Rounded.Person
    }
}

@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp),
    ) {
        var expanded by remember {
            mutableStateOf(true)
        }
        val items = listOf("Episodes", "Locations")
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(140.dp)
                .background(Color.White),
        ) {
            items.forEachIndexed { index, text ->
                DropdownMenuItem(text = {
                    Text(
                        text = text,
                        fontWeight = FontWeight.W300,
                    )
                }, onClick = {
                    navController.navigate(
                        when (text) {
                            "Episodes" -> AppScreens.EpisodesScreen.name
                            else -> AppScreens.LocationsScreen.name
                        },
                    )
                    expanded = false
                    showDialog.value = false
                }, leadingIcon = {
                    Icon(
                        imageVector = when (text) {
                            "Episodes" -> Icons.Rounded.Movie
                            else -> Icons.Rounded.LocationOn
                        },
                        contentDescription = null,
                        tint = Color.LightGray,
                    )
                })
            }
        }
    }
}

@Composable
fun CardCut(modifier: Modifier) {
    Text(
        text = "🎉 CINEMA TICKET 🎉",
        style = TextStyle(
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Black,
        ),
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
            .graphicsLayer {
                shadowElevation = 8.dp.toPx()
                shape = CornerCardCut(64.dp.toPx())
                clip = true
            }
            .background(color = Color.White)
            .padding(start = 32.dp, top = 64.dp, end = 32.dp, bottom = 64.dp),
    )
}
