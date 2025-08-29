package com.team.searchlibrary.feature.books_search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.team.searchlibrary.core.designsystem.theme.SLTheme
import com.team.searchlibrary.feature.books_ui.BookList
import com.team.searchlibrary.feature.books_ui.BookScreenHeader
import com.team.searchlibrary.feature.books_ui.model.BookUiModel

@Composable
internal fun SearchScreen(
    onBookCardClick: (book: BookUiModel) -> Unit,
    onFavoriteClick: (isFavorite: Boolean) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val books = viewModel.searchedBooksWithFavorite.collectAsLazyPagingItems()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    SearchScreen(
        uiState = uiState.value,
        books = books,
        onBookCardClick = onBookCardClick,
        onFavoriteClick = { book ->
            viewModel.updateFavoriteBook(book = book)
            onFavoriteClick(!book.isFavorite)
        },
        onTextChange = viewModel::updateText,
        onSearchClick = viewModel::emitSearchTrigger,
        onSortClick = viewModel::updateSort,
    )
}

@Composable
private fun SearchScreen(
    uiState: SearchUiState,
    books: LazyPagingItems<BookUiModel>,
    onBookCardClick: (book: BookUiModel) -> Unit,
    onFavoriteClick: (book: BookUiModel) -> Unit,
    onTextChange: (input: String) -> Unit,
    onSearchClick: () -> Unit,
    onSortClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = SLTheme.colors.gray200,
            )
    ) {
        BookScreenHeader(
            text = uiState.searchText,
            headerTitle = uiState.headerTitle,
            sortTitle = uiState.sortTitle,
            isFilterChipVisible = uiState.isFilterChipVisible,
            onTextChange = onTextChange,
            onSearchClick = onSearchClick,
            onSortClick = onSortClick,
        )

        BookList(
            books = books,
            onFavoriteClick = onFavoriteClick,
            onBookCardClick = onBookCardClick,
        )
    }
}
