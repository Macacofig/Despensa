package com.ucb.usecases

import com.ucb.data.ProductosRepository
import com.ucb.domain.Producto

class ObtenerProductos(
    val repository: ProductosRepository
) {
    suspend operator fun invoke():List<Producto> =repository.GetProds()
}