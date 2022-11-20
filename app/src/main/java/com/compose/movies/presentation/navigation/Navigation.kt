package com.compose.movies.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.compose.movies.presentation.ui.ui.theme.DetailScreen
import com.compose.movies.presentation.ui.ui.theme.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            HomeScreen(navController)
        }
        composable(
            route = "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })) {
            it.arguments?.getInt("id")?.also {
                DetailScreen(navController = navController)
            }
        }

    }
}