package com.ucb.framework.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "usuarios_guardados")
data class UsuarioGuardado (
    @PrimaryKey(autoGenerate= true)
    val id: Int = 0,
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "password")
    val password: String,
)
