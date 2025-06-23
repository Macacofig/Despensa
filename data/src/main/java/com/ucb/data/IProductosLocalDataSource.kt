package com.ucb.data

import com.ucb.domain.Producto
interface IProductosLocalDataSource {
    suspend fun SaveProd(producto: Producto): Boolean
    suspend fun GetProds(usuarioid: Int): List<Producto>
    suspend fun actualizarProducto(producto: Producto)
    suspend fun DeleteProd(codigoProducto: String): Boolean
    suspend fun obtenerProductoCodigo(codigoProducto: String, usuarioid: Int): Boolean
}