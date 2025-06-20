package com.ucb.despensa.navigation

sealed class screen(val route: String) {
    object PrincipalScreen : screen("principal")
    object AgregarScreen: screen("agregar")
    object EditarScreen: screen("editar")
    object EliminarScreen: screen("eliminar")
}