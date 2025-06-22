package com.ucb.despensa.eliminar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.domain.Producto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.ucb.usecases.ObtenerProductos
import com.ucb.usecases.EliminarProducto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class EliminarViewModel @Inject constructor(
    private val obtenerProductos: ObtenerProductos,
    private val eliminarProducto: EliminarProducto
): ViewModel(){
    sealed class PorductosStateD {
        data class MostrarD(val productos: List<Producto>) : PorductosStateD()
        object NohayProductosD : PorductosStateD()
    }

    private val _stateD = MutableStateFlow<PorductosStateD>(PorductosStateD.NohayProductosD)
    val stateD: StateFlow<PorductosStateD> = _stateD

    fun cargarProductos() {
        viewModelScope.launch {
            val productos = obtenerProductos() // <-- uso del usecase con invoke
            if (productos.isEmpty()) {
                _stateD.value = PorductosStateD.NohayProductosD
            } else {
                _stateD.value = PorductosStateD.MostrarD(productos)
            }
        }
    }
    fun eliminarProducto(codigoProducto: String) {
        viewModelScope.launch {
            eliminarProducto.invoke(codigoProducto)
            cargarProductos()
        }
    }
}