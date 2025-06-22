package com.ucb.despensa.editar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.domain.Producto
import com.ucb.usecases.ObtenerProductos
import com.ucb.usecases.ActualizarProducto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class EditarViewModel @Inject constructor(
    private val obtenerProductos: ObtenerProductos,
    private val actualizarProducto: ActualizarProducto
): ViewModel() {

    sealed class ProductosStateE {
        data class MostrarE(val productos: List<Producto>) : ProductosStateE()
        object NohayProductosE : ProductosStateE()
    }

    private val _stateE = MutableStateFlow<ProductosStateE>(ProductosStateE.NohayProductosE)
    val stateE: StateFlow<ProductosStateE> = _stateE

    fun cargarProductos() {
        viewModelScope.launch {
            val productos = obtenerProductos() // <-- uso del usecase con invoke
            if (productos.isEmpty()) {
                _stateE.value = ProductosStateE.NohayProductosE
            } else {
                _stateE.value = ProductosStateE.MostrarE(productos)
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
