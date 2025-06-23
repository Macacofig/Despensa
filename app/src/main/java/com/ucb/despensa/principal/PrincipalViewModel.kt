package com.ucb.despensa.principal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.domain.Producto
import com.ucb.usecases.ObtenerProductos
import com.ucb.usecases.ObtenerUsuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrincipalViewModel @Inject constructor(
    private val obtenerProductos: ObtenerProductos,
    private val obtenerUsuario: ObtenerUsuario
): ViewModel() {

    sealed class PorductosState {
        data class Mostrar(val productos: List<Producto>) : PorductosState()
        object NohayProductos : PorductosState()
    }

    private val _state = MutableStateFlow<PorductosState>(PorductosState.NohayProductos)
    val state: StateFlow<PorductosState> = _state

    private var usuarioId: Int? = null

    fun inicializar(nombre: String, password: String) {
        viewModelScope.launch {
            val usuario = obtenerUsuario(nombre, password)
            if (usuario != null) {
                usuarioId = usuario.id
                cargarProductos()
            } else {
                _state.value = PorductosState.NohayProductos
            }
        }
    }
    fun cargarProductos() {
        viewModelScope.launch {
            usuarioId?.let { id ->
                val productos = obtenerProductos(id)
                _state.value = if (productos.isEmpty()) {
                    PorductosState.NohayProductos
                } else {
                    PorductosState.Mostrar(productos)
                }
            }
        }
    }
}