package com.team.searchlibrary.feature.books_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.team.searchlibrary.domain.favorite.FavoriteRepository
import com.team.searchlibrary.feature.books_detail.mapper.toUi
import com.team.searchlibrary.feature.books_detail.navigation.BookDetail
import com.team.searchlibrary.feature.books_ui.mapper.toDomain
import com.team.searchlibrary.feature.books_ui.model.BookUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class BookDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val favoriteRepository: FavoriteRepository,
) : ViewModel() {
    private val book = savedStateHandle.toRoute<BookDetail>()

    private val _uiState = MutableStateFlow(book.toUi())
    val uiState = _uiState.asStateFlow()

    fun updateFavoriteBook(book: BookUiModel) {
        viewModelScope.launch {
            val updateBook = book.copy(
                isFavorite = !book.isFavorite
            )
            _uiState.update { updateBook }
            favoriteRepository.saveFavorite(updateBook.toDomain())
        }
    }
}
