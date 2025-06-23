package com.ucb.framework.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IProductoGuardadoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(producto: ProductoGuardado)  // Cambiado a DispGuardado

    @Query("SELECT * FROM productos_guardados WHERE usuario_id = :usuarioid")
    suspend fun GetProductos(usuarioid: Int): List<ProductoGuardado>

    @Query("UPDATE productos_guardados SET cantidad = :nuevaCantidad WHERE codigoProducto = :codigo")
    suspend fun actualizarCantidadPorCodigo(codigo: String, nuevaCantidad: Int)

    @Query("DELETE FROM productos_guardados WHERE codigoProducto = :codproducto")
    suspend fun DeleteProducto(codproducto: String)

    @Query("SELECT COUNT(*) FROM productos_guardados WHERE codigoProducto = :codigoProducto and usuario_id = :usuarioid")
    suspend fun findByCodigo(codigoProducto: String, usuarioid: Int): Int
}