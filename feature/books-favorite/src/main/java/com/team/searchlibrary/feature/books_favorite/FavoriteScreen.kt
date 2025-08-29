package com.team.searchlibrary.feature.books_favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.team.searchlibrary.core.designsystem.theme.SLTheme
import com.team.searchlibrary.feature.books_favorite.component.FilterBottomSheet
import com.team.searchlibrary.feature.books_ui.BookList
import com.team.searchlibrary.feature.books_ui.BookScreenHeader
import com.team.searchlibrary.feature.books_ui.model.BookUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FavoriteScreen(
    onBookCardClick: (book: BookUiModel) -> Unit,
    onFavoriteClick: (isFavorite: Boolean) -> Unit,
    viewModel: FavoriteViewModel = hiltViewModel(),
) {
    val books = viewModel.books.collectAsLazyPagingItems()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    var isBottomSheetVisible by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(isBottomSheetVisible) {
        if (isBottomSheetVisible) {
            viewModel.syncTempFilter()
        }
    }

    FavoriteScreen(
        uiState = uiState.value,
        books = books,
        isBottomSheetVisible = isBottomSheetVisible,
        onFavoriteClick = { book ->
            viewModel.updateFavoriteBook(book)
            onFavoriteClick(!book.isFavorite)
        },
        onBookCardClick = onBookCardClick,
        onTextChange = viewModel::updateText,
        onSearchClick = viewModel::emitSearchTrigger,
        onSortClick = viewModel::updateSort,
        onFilterClick = { isBottomSheetVisible = true },
        onDismissRequest = { isBottomSheetVisible = false },
        onFilterApplyClick = {
            viewModel.updateFilter()
            isBottomSheetVisible = false
        },
        onTempPriceRangeChange = viewModel::updateTempFilter,
        onResetClick = viewModel::resetTempFilter
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FavoriteScreen(
    uiState: FavoriteUiState,
    books: LazyPagingItems<BookUiModel>,
    isBottomSheetVisible: Boolean,
    onFavoriteClick: (book: BookUiModel) -> Unit,
    onBookCardClick: (book: BookUiModel) -> Unit,
    onTextChange: (input: String) -> Unit,
    onSearchClick: () -> Unit,
    onFilterClick: () -> Unit,
    onDismissRequest: () -> Unit,
    onFilterApplyClick: () -> Unit,
    onSortClick: () -> Unit,
    onTempPriceRangeChange: (priceRange: ClosedFloatingPointRange<Float>) -> Unit,
    onResetClick: () -> Unit,
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
            onFilterClick = onFilterClick,
        )

        BookList(
            books = books,
            onFavoriteClick = onFavoriteClick,
            onBookCardClick = onBookCardClick,
        )
    }

    if (isBottomSheetVisible) {
        FilterBottomSheet(
            uiState = uiState,
            onResetClick = onResetClick,
            onDismissRequest = onDismissRequest,
            onFilterApplyClick = onFilterApplyClick,
            onTempPriceRangeChange = onTempPriceRangeChange,
        )
    }
}
