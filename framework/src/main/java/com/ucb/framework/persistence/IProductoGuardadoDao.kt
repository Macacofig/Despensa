package com.ucb.framework.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface IProductoGuardadoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(producto: ProductoGuardado)  // Cambiado a DispGuardado

    @Query("SELECT * FROM productos_guardados")
    suspend fun GetProductos(): List<ProductoGuardado>

    @Update
    suspend fun UpdateProducto(producto: ProductoGuardado)

    @Query("DELETE FROM productos_guardados WHERE codigoProducto = :codproducto")
    suspend fun DeleteProducto(codproducto: String)

    @Query("SELECT * FROM productos_guardados WHERE codigoProducto = :codigoProducto LIMIT 1")
    suspend fun findByCodigo(codigoProducto: String): ProductoGuardado?
}