package com.team.searchlibrary.domain.book.model

import java.time.LocalDate

data class Book(
    val title: String,
    val contents: String,
    val isbn: String,
    val publishedDate: LocalDate,
    val authors: List<String>,
    val publisher: String,
    val price: Int,
    val salePrice: Int,
    val thumbnailUrl: String,
    val isFavorite: Boolean = false,
) {
    val id: String = isbn.trim()
    val stableId: Long = this.hashCode().toUInt().toLong()
}
