package com.team.searchlibrary.feature.books_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.searchlibrary.core.designsystem.theme.SLTheme
import com.team.searchlibrary.feature.books_detail.component.BookDetailTopBar
import com.team.searchlibrary.feature.books_detail.component.BookInformation
import com.team.searchlibrary.feature.books_ui.model.BookUiModel

@Composable
internal fun BookDetailScreen(
    book: BookUiModel,
    onBackClick: () -> Unit,
    onFavoriteClick: (isFavorite: Boolean) -> Unit,
    viewModel: BookDetailViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    BookDetailScreen(
        bookDetail = uiState.value,
        onFavoriteClick = {
            onFavoriteClick(!book.isFavorite)
            viewModel.updateFavoriteBook(book)
        },
        onBackClick = onBackClick,
    )
}

@Composable
private fun BookDetailScreen(
    bookDetail: BookUiModel,
    onFavoriteClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = White)
            .padding(horizontal = 20.dp),
    ) {
        Spacer(modifier = Modifier.height(height = 20.dp))

        BookDetailTopBar(
            isFavorite = bookDetail.isFavorite,
            onFavoriteClick = onFavoriteClick,
            onBackClick = onBackClick,
        )

        Spacer(modifier = Modifier.height(height = 20.dp))

        Text(
            text = bookDetail.title,
            style = SLTheme.typography.head1,
            color = SLTheme.colors.black,
        )

        Spacer(modifier = Modifier.height(height = 12.dp))

        BookInformation(book = bookDetail)

        Spacer(modifier = Modifier.height(height = 20.dp))

        Text(
            text = "책 소개",
            style = SLTheme.typography.head1,
            color = SLTheme.colors.black,
        )

        Spacer(modifier = Modifier.height(height = 12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = SLTheme.colors.gray100,
                    shape = RoundedCornerShape(size = 12.dp),
                )
                .padding(all = 12.dp),
        ) {
            Text(
                text = bookDetail.title,
                style = SLTheme.typography.body4,
                color = SLTheme.colors.black,
                minLines = 10,
                maxLines = 20,
            )
        }
    }
}


@Preview
@Composable
private fun BookDetailScreenPreview() {
    SLTheme {
        BookDetailScreen(
            book = BookUiModel(),
            onFavoriteClick = { },
            onBackClick = { },
        )
    }
}
