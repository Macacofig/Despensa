package com.ucb.despensa.principal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.domain.Producto
import com.ucb.usecases.ObtenerProductos

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrincipalViewModel @Inject constructor(
private val obtenerProductos: ObtenerProductos
): ViewModel() {

    sealed class PorductosState {
        data class Mostrar(val productos: List<Producto>) : PorductosState()
        object NohayProductos : PorductosState()
    }

    private val _state = MutableStateFlow<PorductosState>(PorductosState.NohayProductos)
    val state: StateFlow<PorductosState> = _state

    fun cargarProductos() {
        viewModelScope.launch {
            val productos = obtenerProductos() // <-- uso del usecase con invoke
            if (productos.isEmpty()) {
                _state.value = PorductosState.NohayProductos
            } else {
                _state.value = PorductosState.Mostrar(productos)
            }
        }
    }
}