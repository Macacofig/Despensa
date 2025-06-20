package com.ucb.usecases

import com.ucb.data.ProductosRepository
import com.ucb.domain.Producto

class GuardarProducto(
    val repository: ProductosRepository
) {
    suspend operator fun invoke(producto: Producto) {
        repository.SaveProd(producto)
    }
}