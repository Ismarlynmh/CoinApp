package com.mendozacreations.apicoins.ui.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.mendozacreations.apicoins.data.remote.dto.CoinDto
import com.mendozacreations.apicoins.Model.CoinViewModel
import com.mendozacreations.apicoins.ui.Screen.Components.CoinItem
import com.mendozacreations.apicoins.ui.theme.ApiCoinsTheme

@Composable
fun CoinListScreen(
    navHostController: NavHostController,
    viewModel: CoinViewModel = hiltViewModel()
) {
    val ScaffoldState = rememberScaffoldState()
    Scaffold(
        topBar ={
            TopAppBar(title = { Text( "CryptoApp",
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Black
            ) })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate("RegistroCoinScreen")
                },
                backgroundColor = MaterialTheme.colors.secondary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Coin")
            }
        },
        scaffoldState = ScaffoldState
    ){it

        val state = viewModel.state.value

        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.coins) { coins ->
                    CoinItem(coin = coins, {})
                }
            }

            if (state.isLoading)
                CircularProgressIndicator()
        }
    }
}
