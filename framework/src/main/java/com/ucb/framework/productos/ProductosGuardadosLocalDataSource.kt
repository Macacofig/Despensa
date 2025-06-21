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
    override suspend fun GetProds(): List<Producto> {
        val entities = productoDAO.GetProductos()
        return entities.map { it.toDomain() }
    }

    override suspend fun UpdateProd(producto: Producto): Boolean {
//        val existente = productoDAO.findByCodigo(producto.codigoProducto)
//        return if (existente != null) {
//            val actualizado = existente.copy(
//                nombreProducto = producto.nombreProducto,
//                cantidad = producto.cantidad,
//                usuario_id = producto.usuario_id
//            )
//            productoDAO.UpdateProducto(actualizado)
//            true
//        } else {
//            false
//        }
        return true
    }

    override suspend fun DeleteProd(codigoProducto: String): Boolean {
        productoDAO.DeleteProducto(codigoProducto)
        return true
    }

    override suspend fun obtenerProductoCodigo(codigoProducto: String): Boolean {
        val count = productoDAO.findByCodigo(codigoProducto)
        return count > 0
    }
}