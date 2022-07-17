package com.mendozacreations.apicoins.data.remote

import com.mendozacreations.apicoins.data.remote.dto.CoinDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface CoinAPI {
    @GET("/Coins")
    suspend fun getCoins(): List<CoinDto>
    @POST("/Coins")
    suspend fun postCoin(@Body coinDto: CoinDto): CoinDto
}