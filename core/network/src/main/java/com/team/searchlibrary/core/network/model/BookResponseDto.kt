package com.team.searchlibrary.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookResponseDto(
    @SerialName("title") val title: String,
    @SerialName("contents") val contents: String,
    @SerialName("isbn") val isbn: String,
    @SerialName("datetime") val publishedDate: String,
    @SerialName("authors") val authors: List<String>,
    @SerialName("publisher") val publisher: String,
    @SerialName("price") val price: Int,
    @SerialName("sale_price") val salePrice: Int,
    @SerialName("thumbnail") val thumbnail: String,
)
