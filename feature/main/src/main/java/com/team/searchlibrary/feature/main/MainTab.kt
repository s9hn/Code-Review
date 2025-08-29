package com.team.searchlibrary.feature.main

import androidx.compose.runtime.Composable
import com.team.searchlibrary.core.router.MainTabRoute
import com.team.searchlibrary.core.router.MainTabRoute.Favorite
import com.team.searchlibrary.core.router.MainTabRoute.Search

internal enum class MainTab(
    val iconResId: Int,
    val title: String,
    val route: MainTabRoute,
) {
    SEARCH(
        iconResId = R.drawable.ic_search,
        title = "검색",
        Search,
    ),
    FAVORITE(
        iconResId = R.drawable.ic_favorite,
        title = "즐겨찾기",
        Favorite
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (MainTabRoute) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}
