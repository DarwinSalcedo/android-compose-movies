package com.compose.movies.presentation.navigation

import androidx.navigation.NavType

sealed class NavItem(val baseRoute: String, val navArgs: List<NavArgs> = emptyList()) {
    object MainNavItem : NavItem("Main")
    object MDetailNavItem : NavItem("Detail")
}

enum class NavArgs(val key: String, val navType: NavType<*>) {
    Id("Id", NavType.IntType)
}