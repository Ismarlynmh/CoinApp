package com.mendozacreations.apicoins.Model

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mendozacreations.apicoins.data.remote.ListStateCoin
import com.mendozacreations.apicoins.data.remote.repository.CoinRepository
import com.mendozacreations.apicoins.data.remote.dto.CoinDto
import com.mendozacreations.apicoins.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val coinRepository: CoinRepository
) : ViewModel(){

    var descripcion by mutableStateOf("")
    var imageUrl by mutableStateOf("")
    var valor by mutableStateOf("")

    private var _state = mutableStateOf(ListStateCoin())
    val state: State<ListStateCoin> = _state

    init {
        recargarLista()
    }

    fun recargarLista(){
        coinRepository.getCoin().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = ListStateCoin(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = ListStateCoin(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ListStateCoin(error = result.message ?: "Error desconocido")
                }
            }
        }.launchIn(viewModelScope)


        viewModelScope.launch {
            coinRepository.getCoin().collect {
                _state.value = ListStateCoin(coins = it.data ?: emptyList())
            }
        }

    }

    fun Guardar() {
        viewModelScope.launch {
            coinRepository.Inser(
                CoinDto(
                    descripcion = descripcion,
                    imageUrl = imageUrl,
                    valor = valor
                )
            )
        }
    }
}