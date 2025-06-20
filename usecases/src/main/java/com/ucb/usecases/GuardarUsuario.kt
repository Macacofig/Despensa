package com.ucb.usecases

import com.ucb.data.UsuariosRepository
import com.ucb.domain.Usuario

class GuardarUsuario (
    val repository: UsuariosRepository
) {
    suspend operator fun invoke(usuario: Usuario) {
        repository.SaveUser(usuario)
    }
}