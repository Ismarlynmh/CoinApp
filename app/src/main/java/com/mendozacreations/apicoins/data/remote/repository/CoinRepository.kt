package com.mendozacreations.apicoins.data.remote.repository

import com.mendozacreations.apicoins.data.remote.CoinAPI
import com.mendozacreations.apicoins.data.remote.dto.CoinDto
import com.mendozacreations.apicoins.util.Resource
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val api: CoinAPI
){
    fun getCoin(): Flow<Resource<List<CoinDto>>> = flow {
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val coin = api.getCoins() //descarga las monedas de interneto

            emit(Resource.Success(coin)) //se cargo correctamente y pasarle las monedas
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    suspend fun Inser(coinDto: CoinDto){
        api.postCoin(coinDto)
    }
}