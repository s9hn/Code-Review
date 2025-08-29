package com.team.searchlibrary.feature.main.component

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.team.searchlibrary.feature.books_detail.navigation.bookDetailScreen
import com.team.searchlibrary.feature.books_detail.navigation.navigateToBookDetail
import com.team.searchlibrary.feature.books_favorite.navigation.favoriteScreen
import com.team.searchlibrary.feature.books_search.navigation.searchScreen
import com.team.searchlibrary.feature.main.MainNavigator

@Composable
internal fun MainNavHost(
    mainNavigator: MainNavigator,
    showSnackBar: (isFavorite: Boolean) -> Unit,
) {
    NavHost(
        navController = mainNavigator.navController,
        startDestination = mainNavigator.startDestination,
    ) {
        searchScreen(
            onBookCardClick = mainNavigator.navController::navigateToBookDetail,
            onFavoriteClick = showSnackBar,
        )

        favoriteScreen(
            onBookCardClick = mainNavigator.navController::navigateToBookDetail,
            onFavoriteClick = showSnackBar,
        )

        bookDetailScreen(
            onBackClick = mainNavigator.navController::popBackStack,
            onFavoriteClick = showSnackBar,
        )
    }
}
