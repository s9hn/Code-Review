package com.team.searchlibrary.feature.books_ui.model

data class BookUiModel(
    val id: Long = 0L,
    val title: String = "",
    val contents: String = "",
    val isbn: String = "",
    val publishedDate: String = "",
    val authors: String = "",
    val publisher: String = "",
    val price: Int = 0,
    val salePrice: Int = 0,
    val thumbnailUrl: String = "",
    val isFavorite: Boolean = false,
)