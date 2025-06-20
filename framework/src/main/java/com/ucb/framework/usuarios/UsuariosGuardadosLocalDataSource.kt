package com.ucb.framework.usuarios

import android.content.Context
import com.ucb.data.IUsuariosLocalDataSource
import com.ucb.domain.Producto
import com.ucb.domain.Usuario
import com.ucb.framework.mappers.toDomainU
import com.ucb.framework.mappers.toEntityU
import com.ucb.framework.persistence.AppRoomDatabase
import com.ucb.framework.persistence.IUsuarioGuardadoDao

class UsuariosGuardadosLocalDataSource (
    val context: Context
) : IUsuariosLocalDataSource {

    val usuarioDAO: IUsuarioGuardadoDao = AppRoomDatabase.getDatabase(context).userDao()
    override suspend fun SaveUser(user: Usuario): Boolean {
        usuarioDAO.insert(user.toEntityU())
            return true
    }

    override suspend fun GetUser(nombre: String, password: String): Usuario? {
        val userEntity = usuarioDAO.getUserByNombreAndPassword(nombre, password)
        return userEntity?.toDomainU()
    }
}