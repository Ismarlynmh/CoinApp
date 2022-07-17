package com.mendozacreations.apicoins.util

sealed class Screen (val route: String){

    object RegistroCoinScreen: Screen("RegistroCoinScreen")


    object CoinListScreen: Screen("CoinListScreen")
}