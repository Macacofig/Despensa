package com.ucb.framework.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ucb.domain.Usuario

@Dao
interface IUsuarioGuardadoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usuario: UsuarioGuardado)

    @Query("SELECT * FROM usuarios_guardados WHERE nombre = :nombre and password = :password")
    suspend fun getUserById(nombre: String,password: String): Usuario?

    @Query("SELECT * FROM usuarios_guardados WHERE nombre = :nombre AND password = :password LIMIT 1")
    suspend fun getUserByNombreAndPassword(nombre: String, password: String): UsuarioGuardado?
}
