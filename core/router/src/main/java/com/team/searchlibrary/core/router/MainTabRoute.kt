package com.team.searchlibrary.core.router

import kotlinx.serialization.Serializable

sealed interface MainTabRoute : Route {
    @Serializable
    data object Search : MainTabRoute

    @Serializable
    data object Favorite : MainTabRoute
}
