package com.team.searchlibrary.feature.books_ui.mapper

import com.team.searchlibrary.domain.book.model.Book
import com.team.searchlibrary.feature.books_ui.extensions.toFormattedDate
import com.team.searchlibrary.feature.books_ui.extensions.toLocalDate
import com.team.searchlibrary.feature.books_ui.model.BookUiModel

fun BookUiModel.toDomain(): Book = Book(
    title = title,
    contents = contents,
    isbn = isbn,
    publishedDate = publishedDate.toLocalDate(),
    authors = authors.split(", "),
    publisher = publisher,
    price = price,
    salePrice = salePrice,
    thumbnailUrl = thumbnailUrl,
    isFavorite = isFavorite,
)

fun Book.toUi(): BookUiModel = BookUiModel(
    id = stableId,
    title = title,
    contents = contents,
    isbn = isbn,
    publishedDate = publishedDate.toFormattedDate(),
    authors = authors.joinToString(", "),
    publisher = publisher,
    price = price,
    salePrice = salePrice,
    thumbnailUrl = thumbnailUrl,
    isFavorite = isFavorite,
)

