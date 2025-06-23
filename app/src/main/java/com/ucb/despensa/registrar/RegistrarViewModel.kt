package com.ucb.despensa.registrar

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.ucb.usecases.GuardarUsuario
import com.ucb.usecases.ObtenerUsuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import com.ucb.domain.Usuario
import kotlinx.coroutines.launch

@HiltViewModel
class RegistrarViewModel @Inject constructor(
    private val guardarUsuario: GuardarUsuario,
    private val obtenerUsuario: ObtenerUsuario
):ViewModel()
{
    sealed class RegistroState {
        object Cargando : RegistroState()
        object Registrado : RegistroState()
        data class Error(val mensaje: String) : RegistroState()
        object Idle : RegistroState()
    }

    private val _estado = MutableStateFlow<RegistroState>(RegistroState.Idle)
    val estado: StateFlow<RegistroState> = _estado

    fun registrar(nombre: String, password: String) {
        viewModelScope.launch {
            _estado.value = RegistroState.Cargando

            val yaExiste = obtenerUsuario(nombre, password)
            if (yaExiste != null ) {
                _estado.value = RegistroState.Error("Usuario ya existe.")
            } else {
                val usuario = Usuario(nombre = nombre, password = password)
                guardarUsuario(usuario)
                _estado.value = RegistroState.Registrado
            }
        }
    }
}