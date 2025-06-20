package com.ucb.usecases

import com.ucb.data.UsuariosRepository
import com.ucb.domain.Usuario

class ObtenerUsuario (
    private val repository: UsuariosRepository
) {
    suspend operator fun invoke(nombre: String, password: String): Usuario? {
        return repository.GetUser(nombre, password)
    }
}