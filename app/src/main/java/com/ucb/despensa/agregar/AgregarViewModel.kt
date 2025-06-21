package com.ucb.despensa.agregar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.domain.Producto
import com.ucb.usecases.GuardarProducto
import com.ucb.usecases.BuscarProductoCodigo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgregarViewModel @Inject constructor(
    private val guardarProducto: GuardarProducto,
    private val buscarProductoCodigo: BuscarProductoCodigo
): ViewModel() {

    fun guardarTV(producto: Producto) {
        viewModelScope.launch {
            val existe = buscarProductoCodigo(producto.codigoProducto)
            if (!existe) {
                guardarProducto(producto)
            }
        }
    }
}