package com.team.searchlibrary.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.team.searchlibrary.core.router.Route
import com.team.searchlibrary.feature.books_favorite.navigation.navigateToFavorite
import com.team.searchlibrary.feature.books_search.navigation.navigateToSearch
import com.team.searchlibrary.feature.main.MainTab.FAVORITE
import com.team.searchlibrary.feature.main.MainTab.SEARCH

internal class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination: Route = SEARCH.route

    val currentTab: MainTab?
        @Composable get() = MainTab.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    @Composable
    fun shouldShowBottomBar() = MainTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }

    fun navigate(tab: MainTab) {
        if (navController.currentDestination?.hasRoute(tab.route::class) == true) return

        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
                inclusive = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            SEARCH -> navController.navigateToSearch(navOptions)
            FAVORITE -> navController.navigateToFavorite(navOptions)
        }
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}
