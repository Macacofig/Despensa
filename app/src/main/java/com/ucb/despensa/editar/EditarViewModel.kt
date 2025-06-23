package com.ucb.despensa.editar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.despensa.principal.PrincipalViewModel.PorductosState
import com.ucb.domain.Producto
import com.ucb.usecases.ObtenerProductos
import com.ucb.usecases.ActualizarProducto
import com.ucb.usecases.ObtenerUsuario
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class EditarViewModel @Inject constructor(
    private val obtenerProductos: ObtenerProductos,
    private val actualizarProducto: ActualizarProducto,
    private val obtenerUsuario: ObtenerUsuario
): ViewModel() {

    sealed class ProductosStateE {
        data class MostrarE(val productos: List<Producto>) : ProductosStateE()
        object NohayProductosE : ProductosStateE()
    }

    private val _stateE = MutableStateFlow<ProductosStateE>(ProductosStateE.NohayProductosE)
    val stateE: StateFlow<ProductosStateE> = _stateE

    private var usuarioId: Int? = null

    fun inicializar(nombre: String, password: String) {
        viewModelScope.launch {
            val usuario = obtenerUsuario(nombre, password)
            if (usuario != null) {
                usuarioId = usuario.id
                cargarProductos()
            } else {
                _stateE.value = ProductosStateE.NohayProductosE
            }
        }
    }

    fun cargarProductos() {
        viewModelScope.launch {
            usuarioId?.let { id ->
                val productos = obtenerProductos(id)
                _stateE.value = if (productos.isEmpty()) {
                    ProductosStateE.NohayProductosE
                } else {
                    ProductosStateE.MostrarE(productos)
                }
            }
        }
    }

    fun actualizarCantidad(codigoProducto: String, nuevaCantidad: Int) {
        viewModelScope.launch {
            val productos = (_stateE.value as? ProductosStateE.MostrarE)?.productos ?: return@launch
            val producto = productos.find { it.codigoProducto == codigoProducto }
            if (producto != null) {
                val actualizado = producto.copy(cantidad = nuevaCantidad)
                actualizarProducto(actualizado)
                cargarProductos() // refrescar
            }
        }
    }
}
