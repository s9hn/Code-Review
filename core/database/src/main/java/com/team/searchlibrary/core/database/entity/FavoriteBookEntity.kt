package com.team.searchlibrary.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_books")
data class FavoriteBookEntity(
    @PrimaryKey val id: String,
    val title: String,
    val contents: String,
    val publishedDate: String,
    val isbn: String,
    val authors: List<String>,
    val publisher: String,
    val price: Int,
    val salePrice: Int,
    val thumbnailUrl: String,
    val isFavorite: Boolean,
)
