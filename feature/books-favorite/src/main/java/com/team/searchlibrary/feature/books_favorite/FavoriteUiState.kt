package com.team.searchlibrary.feature.books_favorite

import com.team.searchlibrary.domain.favorite.model.Sort
import com.team.searchlibrary.domain.favorite.model.Sort.ASCENDING
import com.team.searchlibrary.domain.favorite.model.Sort.DESCENDING
import com.team.searchlibrary.feature.books_ui.extensions.toFormattedPrice

internal data class FavoriteUiState(
    val headerTitle: String = "즐겨찾기",
    val searchText: String = "",
    val isFilterChipVisible: Boolean = true,
    val sort: Sort = ASCENDING,
    val fullPriceRange: Pair<Float, Float> = 0f to 300_000f,
    val priceRange: Pair<Float, Float> = 0f to 300_000f,
    val tempPriceRange: Pair<Float, Float> = priceRange,
) {
    val sortTitle = when (sort) {
        ASCENDING -> "오름차순(제목)"
        DESCENDING -> "내림차순(제목)"
    }

    val isPriceRangeChanged: Boolean =
        tempPriceRange.first != priceRange.first || tempPriceRange.second != priceRange.second

    val currentPriceRangeText: String =
        "${tempPriceRange.first.toInt().toFormattedPrice()} - ${
            tempPriceRange.second.toInt().toFormattedPrice()
        }"

    val fullMinPriceText: String = fullPriceRange.first.toInt().toFormattedPrice()
    val fullMaxPriceText: String = fullPriceRange.second.toInt().toFormattedPrice()

    val sliderValueRange: ClosedFloatingPointRange<Float> = fullPriceRange.first..fullPriceRange.second
    val sliderCurrentValue: ClosedFloatingPointRange<Float> = tempPriceRange.first..tempPriceRange.second
}
