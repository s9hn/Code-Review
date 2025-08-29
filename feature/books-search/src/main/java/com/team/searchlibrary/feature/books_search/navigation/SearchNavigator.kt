package com.team.searchlibrary.feature.books_search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.team.searchlibrary.core.router.MainTabRoute.Search
import com.team.searchlibrary.feature.books_search.SearchScreen
import com.team.searchlibrary.feature.books_ui.model.BookUiModel

fun NavGraphBuilder.searchScreen(
    onBookCardClick: (book: BookUiModel) -> Unit,
    onFavoriteClick: (isFavorite: Boolean) -> Unit,
) {
    composable<Search> {
        SearchScreen(
            onBookCardClick = onBookCardClick,
            onFavoriteClick = onFavoriteClick,
        )
    }
}

fun NavController.navigateToSearch(navOptions: NavOptions) {
    navigate(route = Search, navOptions = navOptions)
}
