package com.ucb.despensa.eliminar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.despensa.principal.PrincipalViewModel.PorductosState
import com.ucb.domain.Producto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.ucb.usecases.ObtenerProductos
import com.ucb.usecases.EliminarProducto
import com.ucb.usecases.ObtenerUsuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class EliminarViewModel @Inject constructor(
    private val obtenerProductos: ObtenerProductos,
    private val eliminarProducto: EliminarProducto,
    private val obtenerUsuario: ObtenerUsuario
): ViewModel(){
    sealed class PorductosStateD {
        data class MostrarD(val productos: List<Producto>) : PorductosStateD()
        object NohayProductosD : PorductosStateD()
    }

    private val _stateD = MutableStateFlow<PorductosStateD>(PorductosStateD.NohayProductosD)
    val stateD: StateFlow<PorductosStateD> = _stateD

    private var usuarioId: Int? = null

    fun inicializar(nombre: String, password: String) {
        viewModelScope.launch {
            val usuario = obtenerUsuario(nombre, password)
            if (usuario != null) {
                usuarioId = usuario.id
                cargarProductos()
            } else {
                _stateD.value = PorductosStateD.NohayProductosD
            }
        }
    }

    fun cargarProductos() {
        viewModelScope.launch {
            usuarioId?.let { id ->
                val productos = obtenerProductos(id)
                _stateD.value = if (productos.isEmpty()) {
                    PorductosStateD.NohayProductosD
                } else {
                    PorductosStateD.MostrarD(productos)
                }
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