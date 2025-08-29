package com.team.searchlibrary.feature.books_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.team.searchlibrary.core.designsystem.theme.SLTheme
import com.team.searchlibrary.feature.books_ui.component.BookCard
import com.team.searchlibrary.feature.books_ui.model.BookUiModel

@Composable
fun BookList(
    books: LazyPagingItems<BookUiModel>,
    onBookCardClick: (book: BookUiModel) -> Unit,
    onFavoriteClick: (book: BookUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 8.dp)
                .align(alignment = Alignment.TopCenter)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            SLTheme.colors.blackAlpha20,
                            SLTheme.colors.transparent,
                        ),
                    ),
                ),
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 4.dp)
                .align(alignment = Alignment.BottomCenter)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            SLTheme.colors.transparent,
                            SLTheme.colors.blackAlpha10,
                        ),
                    ),
                ),
        )

        LazyColumn(
            contentPadding = PaddingValues(all = 12.dp),
            verticalArrangement = Arrangement.spacedBy(space = 12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = SLTheme.colors.gray200),
        ) {
            items(
                count = books.itemCount,
            ) {
                books[it]?.let { book ->
                    BookCard(
                        book = book,
                        onFavoriteClick = { onFavoriteClick(book) },
                        onBookCardClick = onBookCardClick,
                    )
                }
            }
        }
    }
}
