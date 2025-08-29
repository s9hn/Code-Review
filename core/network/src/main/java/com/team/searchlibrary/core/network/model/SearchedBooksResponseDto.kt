package com.team.searchlibrary.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchedBooksResponseDto(
    @SerialName("meta") val meta: MetaResponseDto,
    @SerialName("documents") val books: List<BookResponseDto>,
)
