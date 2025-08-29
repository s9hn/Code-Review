package com.team.searchlibrary.data.search.mapper

import com.team.searchlibrary.core.network.model.BookResponseDto
import com.team.searchlibrary.domain.book.model.Book
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME

internal fun BookResponseDto.toDomain(): Book = Book(
    isbn = isbn,
    title = title,
    authors = authors,
    publisher = publisher,
    publishedDate = LocalDateTime
        .parse(publishedDate, ISO_OFFSET_DATE_TIME)
        .toLocalDate(),
    price = price,
    salePrice = salePrice,
    thumbnailUrl = thumbnail,
    contents = contents,
)
