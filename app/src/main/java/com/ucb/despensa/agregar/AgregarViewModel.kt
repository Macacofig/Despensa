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

    sealed class AgregarState {
        object ProductoGuardado : AgregarState()
        object ProductoYaExiste : AgregarState()
        object Error : AgregarState()
        object Idle : AgregarState()
    }

    private val _estado = MutableStateFlow<AgregarState>(AgregarState.Idle)
    val estado: StateFlow<AgregarState> = _estado

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
                val Prodexiste = buscarProductoCodigo(prodConUsuario.codigoProducto, id)
                if (!Prodexiste) {
                    guardarProducto(prodConUsuario)
                    _estado.value = AgregarState.ProductoGuardado
                } else {
                    _estado.value = AgregarState.ProductoYaExiste
                }
            } ?: run {
                _estado.value = AgregarState.Error
            }
        }
    }

    fun reiniciarEstado() {
        _estado.value = AgregarState.Idle
    }
}