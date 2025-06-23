package com.ucb.despensa.navigation

sealed class screen(val route: String) {
    object PrincipalScreen : screen("principal/{nombre}/{password}") {
        fun createRoute(nombre: String, password: String): String {
            return "principal/$nombre/$password"
        }
    }
    object AgregarScreen : screen("agregar/{nombre}/{password}") {
        fun createRoute(nombre: String, password: String) = "agregar/$nombre/$password"
    }
    object EditarScreen: screen("editar")
    object EliminarScreen: screen("eliminar")
    object PrimeraScreen: screen("primera")
    object LoginScreen: screen("login")
    object RegistrarScreen: screen("registrar")
}