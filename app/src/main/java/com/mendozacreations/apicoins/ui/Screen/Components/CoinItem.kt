package com.mendozacreations.apicoins.ui.Screen.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mendozacreations.apicoins.data.remote.dto.CoinDto
import com.mendozacreations.apicoins.ui.theme.ApiCoinsTheme
import java.text.DecimalFormat

@Composable
fun CoinItem (
    coin: CoinDto,
    onClick : (CoinDto) -> Unit
) {
    Card(
    shape = RoundedCornerShape(8.dp),
    elevation = 5.dp,
    modifier = Modifier
        .padding(horizontal = 8.dp, vertical = 2.dp)
        .fillMaxWidth()
    ) {
        Row(modifier = Modifier
            .clickable { onClick(coin) }
            .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row{
                AsyncImage(modifier = Modifier.size(55.dp),
                    model = ImageRequest.Builder(LocalContext.current).data(coin.imageUrl)
                        .crossfade(true).build(),
                    contentDescription = coin.descripcion,
                )

                Spacer(modifier = Modifier.width(5.dp))
                Text(text = coin.descripcion, fontWeight = FontWeight.Bold, modifier = Modifier.padding(2.dp))
            }

            val decimal = DecimalFormat("#,###.######")

            Text(
                text = "$" + decimal.format(coin.valor?.toDouble()), color = Color.Gray, fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.End
            )
        }
    }
}

