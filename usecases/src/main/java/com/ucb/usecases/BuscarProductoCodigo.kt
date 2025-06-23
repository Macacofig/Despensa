package com.ucb.usecases

import com.ucb.data.ProductosRepository

class BuscarProductoCodigo (
    private val repository: ProductosRepository
){
    suspend operator fun invoke(codigoProducto: String, usuarioid: Int): Boolean {
        return repository.obtenerProductoCodigo(codigoProducto, usuarioid)
    }
}