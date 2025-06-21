package com.ucb.usecases

import com.ucb.data.ProductosRepository
import com.ucb.domain.Producto
class BuscarProductoCodigo (
    private val repository: ProductosRepository
){
    suspend operator fun invoke(codigoProducto: String): Boolean {
        return repository.obtenerProductoCodigo(codigoProducto)
    }
}