package com.team.searchlibrary.data.favorite.mapper

import com.team.searchlibrary.core.database.entity.FavoriteBookEntity
import com.team.searchlibrary.domain.book.model.Book
import java.time.LocalDate
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE

internal fun Book.toEntity(): FavoriteBookEntity =
    FavoriteBookEntity(
        id = id,
        title = title,
        contents = contents,
        publishedDate = publishedDate.toString(),
        isbn = isbn,
        authors = authors,
        publisher = publisher,
        price = price,
        salePrice = salePrice,
        thumbnailUrl = thumbnailUrl,
        isFavorite = isFavorite,
    )

internal fun FavoriteBookEntity.toDomain(): Book =
    Book(
        title = title,
        contents = contents,
        isbn = isbn,
        publishedDate = LocalDate.parse(publishedDate, ISO_LOCAL_DATE),
        authors = authors,
        publisher = publisher,
        price = price,
        salePrice = salePrice,
        thumbnailUrl = thumbnailUrl,
        isFavorite = isFavorite,
    )
