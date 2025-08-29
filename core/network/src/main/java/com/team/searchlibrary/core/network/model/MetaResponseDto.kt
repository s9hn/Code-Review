package com.team.searchlibrary.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetaResponseDto(
    @SerialName("is_end") val isEnd: Boolean,
    @SerialName("pageable_count") val pageableCount: Int,
    @SerialName("total_count") val totalCount: Int,
)
