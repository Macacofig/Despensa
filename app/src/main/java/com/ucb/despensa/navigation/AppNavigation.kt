package com.ucb.despensa.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ucb.despensa.agregar.AgregarUI
import com.ucb.despensa.editar.EditarUI
import com.ucb.despensa.eliminar.EliminarUI
import com.ucb.despensa.principal.PrincipalUI

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = screen.PrincipalScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    )
    {
        composable(screen.PrincipalScreen.route)
        {
            PrincipalUI(
                onAgregarClick = {
                    navController.navigate(screen.AgregarScreen.route)
                },
                onEditarClick = {
                    navController.navigate(screen.EditarScreen.route)
                },
                onEliminarClick = {
                    navController.navigate(screen.EliminarScreen.route)
                }
            )
        }


        // Codigo para navegar hacia atras
        composable(screen.AgregarScreen.route) {
            AgregarUI(
                onBackClick = { navController.popBackStack() },
            )
        }
        composable(screen.EditarScreen.route) {
            EditarUI(
                onBackClick = { navController.popBackStack() },
            )
        }
        composable(screen.EliminarScreen.route) {
            EliminarUI(
                onBackClick = { navController.popBackStack() },
            )
        }


    }
}