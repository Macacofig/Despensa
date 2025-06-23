package com.ucb.framework.productos

import android.content.Context
import androidx.room.util.copy
import com.ucb.data.IProductosLocalDataSource
import com.ucb.domain.Producto
import com.ucb.framework.mappers.toDomain
import com.ucb.framework.mappers.toEntity
import com.ucb.framework.persistence.AppRoomDatabase
import com.ucb.framework.persistence.IProductoGuardadoDao

class ProductosGuardadosLocalDataSource (
    val context: Context
) : IProductosLocalDataSource{

    val productoDAO: IProductoGuardadoDao = AppRoomDatabase.getDatabase(context).prodDao()

    override suspend fun SaveProd(dispositivo: Producto): Boolean {
        productoDAO.insert(dispositivo.toEntity())
        return true
    }
    override suspend fun GetProds(usuarioid: Int): List<Producto> {
        val entities = productoDAO.GetProductos(usuarioid)
        return entities.map { it.toDomain() }
    }

    override suspend fun actualizarProducto(producto: Producto) {
        productoDAO.actualizarCantidadPorCodigo(producto.codigoProducto, producto.cantidad)
    }

    override suspend fun DeleteProd(codigoProducto: String): Boolean {
        productoDAO.DeleteProducto(codigoProducto)
        return true
    }

    override suspend fun obtenerProductoCodigo(codigoProducto: String, usuarioid: Int): Boolean {
        return productoDAO.findByCodigo(codigoProducto, usuarioid) > 0
    }
}