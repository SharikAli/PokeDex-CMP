package org.example.pokedex.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.pokedex.domain.model.Menu
import org.example.pokedex.presentation.theme.RoundedCornerLarge
import org.jetbrains.compose.resources.painterResource
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.pokeball_grey

@Composable
fun MenuItem(
    modifier: Modifier = Modifier,
    menu: Menu,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier.fillMaxSize(),
        onClick = onClick,
        shape = RoundedCornerLarge
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(menu.color)
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = menu.title,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    style = TextStyle(
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )

                Image(
                    painter = painterResource(Res.drawable.pokeball_grey),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .alpha(0.5f)
                )
            }
        }
    }
}
