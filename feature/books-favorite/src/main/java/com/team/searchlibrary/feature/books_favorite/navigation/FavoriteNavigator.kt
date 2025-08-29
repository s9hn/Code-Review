package com.team.searchlibrary.feature.books_favorite.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.team.searchlibrary.core.router.MainTabRoute.Favorite
import com.team.searchlibrary.feature.books_favorite.FavoriteScreen
import com.team.searchlibrary.feature.books_ui.model.BookUiModel

fun NavGraphBuilder.favoriteScreen(
    onBookCardClick: (book: BookUiModel) -> Unit,
    onFavoriteClick: (isFavorite: Boolean) -> Unit,
) {
    composable<Favorite> {
        FavoriteScreen(
            onBookCardClick = onBookCardClick,
            onFavoriteClick = onFavoriteClick,
        )
    }
}

fun NavController.navigateToFavorite(navOptions: NavOptions) {
    navigate(route = Favorite, navOptions = navOptions)
}
