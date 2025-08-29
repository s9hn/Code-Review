package com.team.searchlibrary.feature.books_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.team.searchlibrary.domain.favorite.FavoriteRepository
import com.team.searchlibrary.domain.search.SearchRepository
import com.team.searchlibrary.domain.search.model.Sort.ACCURACY
import com.team.searchlibrary.domain.search.model.Sort.LATEST
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
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val favoriteRepository: FavoriteRepository,
) : ViewModel() {

    private val input = MutableStateFlow("")
    private val bookSort = MutableStateFlow(ACCURACY)
    private val searchTrigger = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    private val debouncedInput = input
        .map(String::trim)
        .debounce(300)
        .distinctUntilChanged()

    private val searchedBooks = merge(
        debouncedInput,
        searchTrigger.map { input.value.trim() }
    ).combine(bookSort) { query, sort ->
        query to sort
    }.distinctUntilChanged()
        .flatMapLatest { (query, sort) ->
            if (query.isBlank()) {
                flowOf(PagingData.empty())
            } else {
                searchRepository.search(query = query, sort = sort)
            }
        }.cachedIn(viewModelScope)

    val searchedBooksWithFavorite = combine(
        searchedBooks,
        favoriteRepository.getFavoriteBookIds()
    ) { pagingData, favoriteIds ->
        val favoriteSet = favoriteIds.toSet()
        pagingData.map { book ->
            book.copy(isFavorite = book.id in favoriteSet).toUi()
        }
    }

    val uiState = combine(
        input.asStateFlow(),
        bookSort.asStateFlow(),
    ) { searchText, sort ->
        SearchUiState(
            searchText = searchText,
            sort = sort,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SearchUiState()
    )

    fun updateText(text: String) {
        input.update { text }
    }

    fun updateSort() {
        bookSort.update {
            when (it) {
                ACCURACY -> LATEST
                LATEST -> ACCURACY
            }
        }
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
