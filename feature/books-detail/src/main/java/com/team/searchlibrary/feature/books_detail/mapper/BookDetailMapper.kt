package com.team.searchlibrary.feature.books_detail.mapper

import com.team.searchlibrary.feature.books_detail.navigation.BookDetail
import com.team.searchlibrary.feature.books_ui.model.BookUiModel

internal fun BookDetail.toUi(): BookUiModel = BookUiModel(
    id = id,
    title = title,
    contents = contents,
    isbn = isbn,
    publishedDate = publishedDate,
    authors = authors,
    publisher = publisher,
    price = price,
    salePrice = salePrice,
    thumbnailUrl = thumbnailUrl,
    isFavorite = isFavorite,
)
