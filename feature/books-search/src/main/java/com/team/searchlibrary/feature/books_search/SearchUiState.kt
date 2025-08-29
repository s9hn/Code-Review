package com.team.searchlibrary.feature.books_search

import com.team.searchlibrary.domain.search.model.Sort

internal data class SearchUiState(
    val headerTitle: String = "검색",
    val isFilterChipVisible: Boolean = false,
    val searchText: String = "",
    val sort: Sort = Sort.ACCURACY,
) {
    val sortTitle = when (sort) {
        Sort.ACCURACY -> "정확도순"
        Sort.LATEST -> "출간일순"
    }
}
