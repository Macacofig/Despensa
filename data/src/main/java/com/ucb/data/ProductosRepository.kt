package com.ucb.data

import com.ucb.domain.Producto

class ProductosRepository(
    private val localDataSource: IProductosLocalDataSource
){
    suspend fun SaveProd(producto: Producto): Boolean{
        return localDataSource.SaveProd(producto)
    }
    suspend fun GetProds(): List<Producto>{
        return localDataSource.GetProds()
    }
    suspend fun UpdateProd(producto: Producto): Boolean{
        this.localDataSource.UpdateProd(producto)
        return true
    }
    suspend fun DeleteProd(codigoProducto: String): Boolean{
        this.localDataSource.DeleteProd(codigoProducto)
        return true
    }
}