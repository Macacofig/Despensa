package com.ucb.data

import com.ucb.domain.Usuario

class UsuariosRepository (
    private val localDataSource: IUsuariosLocalDataSource
){
    suspend fun SaveUser(user: Usuario) : Boolean{
        return localDataSource.SaveUser(user)
    }
    suspend fun GetUser(nombre: String, password: String): Usuario?{
        return localDataSource.GetUser(nombre,password)
    }
}