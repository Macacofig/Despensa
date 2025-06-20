package com.ucb.framework.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "productos_guardados")
data class ProductoGuardado (
    @PrimaryKey(autoGenerate= true)
    val id: Int = 0,
    @ColumnInfo(name = "nombreProducto")
    val nombreProducto: String,
    @ColumnInfo(name = "codigoProducto")
    val codigoProducto: String,
    @ColumnInfo(name = "cantidad")
    val cantidad : Int,
    @ColumnInfo(name = "usuario_id")
    val usuario_id : Int
)

