package com.ucb.data

import com.ucb.domain.Usuario

interface IUsuariosLocalDataSource {
    suspend fun SaveUser(user: Usuario) : Boolean
    suspend fun GetUser(nombre: String, password: String): Usuario?
}