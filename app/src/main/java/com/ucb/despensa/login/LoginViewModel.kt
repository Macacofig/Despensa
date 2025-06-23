package com.ucb.despensa.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.domain.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.ucb.usecases.ObtenerUsuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val obtenerUsuario: ObtenerUsuario
) : ViewModel() {
    sealed class LoginState {
        object Idle : LoginState()
        object Cargando : LoginState()
        data class Exitoso(val usuario: Usuario) : LoginState()
        data class Error(val mensaje: String) : LoginState()
    }

    private val _estado = MutableStateFlow<LoginState>(LoginState.Idle)
    val estado: StateFlow<LoginState> = _estado

    fun login(nombre: String, password: String) {
        viewModelScope.launch {
            _estado.value = LoginState.Cargando
            val usuarioexiste = obtenerUsuario(nombre,password)
            if (usuarioexiste != null) {
                _estado.value = LoginState.Exitoso(usuarioexiste)
            } else {
                _estado.value = LoginState.Error("Credenciales incorrectas")
            }
        }
    }

    fun limpiarEstado() {
        _estado.value = LoginState.Idle
    }
}