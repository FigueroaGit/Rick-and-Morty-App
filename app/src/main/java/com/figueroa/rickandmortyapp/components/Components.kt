package com.figueroa.rickandmortyapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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
                        showToast(
                            context = context,
                            message = "Coming soon",
                        )
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
            .fillMaxSize().padding(8.dp)
            .graphicsLayer {
                shadowElevation = 8.dp.toPx()
                shape = CornerCardCut(64.dp.toPx())
                clip = true
            }
            .background(color = Color.White)
            .padding(start = 32.dp, top = 64.dp, end = 32.dp, bottom = 64.dp)
    )
}
