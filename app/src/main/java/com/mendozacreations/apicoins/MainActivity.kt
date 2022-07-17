package com.mendozacreations.apicoins

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.mendozacreations.apicoins.ui.theme.ApiCoinsTheme
import com.mendozacreations.apicoins.ui.Screen.CoinListScreen
import com.mendozacreations.apicoins.ui.Screen.RegistroCoinScreen
import com.mendozacreations.apicoins.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiCoinsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApps()
                }
            }
        }
    }
}

@Composable
fun MyApps() {
    ApiCoinsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val navHostController = rememberNavController()

            NavHost(navController = navHostController, startDestination = Screen.CoinListScreen.route) {
                composable(route = Screen.CoinListScreen.route) {
                    CoinListScreen(navHostController = navHostController)
                }
                composable(route = Screen.RegistroCoinScreen.route){
                    RegistroCoinScreen(navHostController = navHostController)
                }
            }
        }
    }
}

