package com.ucb.data

import com.ucb.domain.Producto

class ProductosRepository(
    private val localDataSource: IProductosLocalDataSource
){
    suspend fun SaveProd(producto: Producto): Boolean{
        return localDataSource.SaveProd(producto)
    }
    suspend fun GetProds(usuarioid: Int): List<Producto>{
        return localDataSource.GetProds(usuarioid)
    }
    suspend fun UpdateProd(producto: Producto) {
        localDataSource.actualizarProducto(producto)
    }
    suspend fun DeleteProd(codigoProducto: String): Boolean{
        localDataSource.DeleteProd(codigoProducto)
        return true
    }
    suspend fun obtenerProductoCodigo(codigoProducto: String, usuarioid: Int): Boolean{
        return localDataSource.obtenerProductoCodigo(codigoProducto, usuarioid)
    }
}