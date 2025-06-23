package com.ucb.despensa.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ucb.despensa.agregar.AgregarUI
import com.ucb.despensa.editar.EditarUI
import com.ucb.despensa.eliminar.EliminarUI
import com.ucb.despensa.intro.PrimeraUI
import com.ucb.despensa.login.LoginUI
import com.ucb.despensa.principal.PrincipalUI
import com.ucb.despensa.registrar.RegistrarUI

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = screen.PrimeraScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    )
    {
        composable (screen.PrimeraScreen.route)
        {

            PrimeraUI(
                onInicioSesionClick = {
                    navController.navigate(screen.LoginScreen.route)
                },
                onRegistrarClick = {
                    navController.navigate(screen.RegistrarScreen.route)
                }

            )
        }
        composable (screen.LoginScreen.route)
        {
            LoginUI(
                navController = navController,
                onBackClick = {
                    navController.navigate(screen.PrimeraScreen.route)
                }
            )
        }
        composable (screen.RegistrarScreen.route)
        {
            RegistrarUI(
                navController = navController,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(
            route = screen.PrincipalScreen.route,
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("password") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val password = backStackEntry.arguments?.getString("password") ?: ""

            PrincipalUI(
                nombre = nombre,
                password = password,
                onAgregarClick = {
                    navController.navigate(screen.AgregarScreen.createRoute(nombre, password))},
                onEditarClick = {
                    navController.navigate(screen.EditarScreen.createRoute(nombre,password))},
                onEliminarClick = {
                    navController.navigate(screen.EliminarScreen.createRoute(nombre,password))}
            )
        }


        composable(
            route = screen.AgregarScreen.route, // "agregar/{nombre}/{password}"
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("password") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val password = backStackEntry.arguments?.getString("password") ?: ""

            AgregarUI(
                nombre = nombre,
                password = password,
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(
            route = screen.EditarScreen.route,
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("password") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val password = backStackEntry.arguments?.getString("password") ?: ""
            EditarUI(
                nombre = nombre,
                password = password,
                onBackClick = { navController.popBackStack() },
            )
        }
        composable(
            route = screen.EliminarScreen.route,
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("password") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val password = backStackEntry.arguments?.getString("password") ?: ""
            EliminarUI(
                nombre = nombre,
                password = password,
                onBackClick = { navController.popBackStack() },
            )
        }


    }
}