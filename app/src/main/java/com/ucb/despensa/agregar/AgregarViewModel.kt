package com.ucb.despensa.agregar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.domain.Producto
import com.ucb.usecases.GuardarProducto
import com.ucb.usecases.BuscarProductoCodigo
import com.ucb.usecases.ObtenerUsuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgregarViewModel @Inject constructor(
    private val guardarProducto: GuardarProducto,
    private val buscarProductoCodigo: BuscarProductoCodigo,
    private val obtenerUsuario: ObtenerUsuario
): ViewModel() {

    private var usuarioId: Int? = null

    private val _usuarioCargado = MutableStateFlow(false)
    val usuarioCargado: StateFlow<Boolean> = _usuarioCargado

    fun inicializar(nombre: String, password: String) {
        viewModelScope.launch {
            val usuario = obtenerUsuario(nombre, password)
            usuarioId = usuario?.id
            _usuarioCargado.value = usuarioId != null
        }
    }

    fun guardarProd(producto: Producto) {
        viewModelScope.launch {
            usuarioId?.let { id ->
                val prodConUsuario = producto.copy(usuario_id = id)
                guardarProducto(prodConUsuario)
            }
        }
    }
}