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
    object EditarScreen: screen("editar/{nombre}/{password}")
    {
        fun createRoute(nombre: String, password: String) = "editar/$nombre/$password"
    }
    object EliminarScreen: screen("eliminar/{nombre}/{password}")
    {
        fun createRoute(nombre: String, password: String) = "eliminar/$nombre/$password"
    }
    object PrimeraScreen: screen("primera")
    object LoginScreen: screen("login")
    object RegistrarScreen: screen("registrar")
}