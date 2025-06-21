package com.ucb.data

import com.ucb.domain.Producto
interface IProductosLocalDataSource {
    suspend fun SaveProd(producto: Producto): Boolean
    suspend fun GetProds(): List<Producto>
    suspend fun UpdateProd(producto: Producto): Boolean
    suspend fun DeleteProd(codigoProducto: String): Boolean
    suspend fun obtenerProductoCodigo(codigoProducto: String): Boolean
}