package com.team.searchlibrary.feature.books_favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.team.searchlibrary.domain.book.model.Book
import com.team.searchlibrary.domain.favorite.FavoriteRepository
import com.team.searchlibrary.domain.favorite.model.Sort
import com.team.searchlibrary.domain.favorite.model.Sort.ASCENDING
import com.team.searchlibrary.domain.favorite.model.Sort.DESCENDING
import com.team.searchlibrary.feature.books_ui.mapper.toDomain
import com.team.searchlibrary.feature.books_ui.mapper.toUi
import com.team.searchlibrary.feature.books_ui.model.BookUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
) : ViewModel() {
    private data class SearchQuery(
        val query: String,
        val sort: Sort,
        val minPrice: Int,
        val maxPrice: Int,
    )

    companion object {
        private val DEFAULT_FULL_RANGE = 0f to 300_000f
    }

    private val input = MutableStateFlow("")
    private val bookSort = MutableStateFlow(ASCENDING)
    private val priceFilter = MutableStateFlow(DEFAULT_FULL_RANGE)
    private val tempPriceFilter = MutableStateFlow(DEFAULT_FULL_RANGE)
    private val searchTrigger = MutableSharedFlow<Unit>(extraBufferCapacity = 1)

    private val debouncedInput = input
        .map(String::trim)
        .debounce(300)
        .distinctUntilChanged()

    private val searchQuery = combine(
        merge(
            debouncedInput,
            searchTrigger.map { input.value.trim() }
        ),
        bookSort,
        priceFilter
    ) { query, sort, (minPrice, maxPrice) ->
        SearchQuery(
            query = query,
            sort = sort,
            minPrice = minPrice.toInt(),
            maxPrice = maxPrice.toInt()
        )
    }.distinctUntilChanged()

    val books = searchQuery
        .flatMapLatest { query ->
            favoriteRepository.getFavoriteBooks(
                query = query.query,
                sort = query.sort,
                minPrice = query.minPrice,
                maxPrice = query.maxPrice
            )
        }
        .map { pagingData -> pagingData.map(Book::toUi) }
        .cachedIn(viewModelScope)

    val uiState = combine(
        input.asStateFlow(),
        bookSort.asStateFlow(),
        priceFilter.asStateFlow(),
        tempPriceFilter.asStateFlow()
    ) { searchText, sort, priceRange, tempPriceRange ->
        FavoriteUiState(
            searchText = searchText,
            sort = sort,
            fullPriceRange = DEFAULT_FULL_RANGE,
            priceRange = priceRange,
            tempPriceRange = tempPriceRange
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FavoriteUiState()
    )

    fun updateTempFilter(priceRange: ClosedFloatingPointRange<Float>) {
        val roundedRange =
            priceRange.start.roundToThousand() to priceRange.endInclusive.roundToThousand()
        tempPriceFilter.update { roundedRange }
    }

    private fun Float.roundToThousand(): Float = kotlin.math.round(this / 1000f) * 1000f

    fun resetTempFilter() {
        tempPriceFilter.update { DEFAULT_FULL_RANGE }
    }

    fun updateText(text: String) {
        input.update { text }
    }

    fun updateSort() {
        bookSort.update {
            when (it) {
                ASCENDING -> DESCENDING
                DESCENDING -> ASCENDING
            }
        }
    }

    fun updateFilter() {
        priceFilter.update { tempPriceFilter.value }
    }

    fun syncTempFilter() {
        tempPriceFilter.update { priceFilter.value }
    }

    fun updateFavoriteBook(book: BookUiModel) {
        viewModelScope.launch {
            val updateBook = book.copy(
                isFavorite = !book.isFavorite
            )
            favoriteRepository.saveFavorite(updateBook.toDomain())
        }
    }

    fun emitSearchTrigger() {
        searchTrigger.tryEmit(Unit)
    }
}
