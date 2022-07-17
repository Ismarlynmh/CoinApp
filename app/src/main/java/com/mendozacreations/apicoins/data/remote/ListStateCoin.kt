package com.mendozacreations.apicoins.data.remote

import com.mendozacreations.apicoins.data.remote.dto.CoinDto

data class ListStateCoin(
    val isLoading: Boolean = false,
    val coins: List<CoinDto> = emptyList(),
    val error: String = ""
)