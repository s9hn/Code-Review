package com.team.searchlibrary.feature.books_ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.searchlibrary.core.designsystem.component.NetworkImage
import com.team.searchlibrary.core.designsystem.theme.SLTheme
import com.team.searchlibrary.feature.books_ui.BookDetailText
import com.team.searchlibrary.feature.books_ui.FavoriteToggleButton
import com.team.searchlibrary.feature.books_ui.extensions.toFormattedPrice
import com.team.searchlibrary.feature.books_ui.model.BookUiModel

@Composable
internal fun BookCard(
    book: BookUiModel,
    onBookCardClick: (book: BookUiModel) -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        onClick = { onBookCardClick(book) },
        shape = RoundedCornerShape(size = 16.dp),
        color = SLTheme.colors.white,
        modifier = modifier.fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier.padding(all = 8.dp),
        ) {
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.fillMaxWidth(),
            ) {
                NetworkImage(
                    imageUrl = book.thumbnailUrl,
                    modifier = Modifier
                        .size(
                            width = 74.dp,
                            height = 108.dp,
                        )
                        .border(
                            border = BorderStroke(
                                width = 1.dp,
                                color = SLTheme.colors.gray150
                            ),
                            shape = RoundedCornerShape(size = 12.dp),
                        )
                        .clip(shape = RoundedCornerShape(size = 12.dp))
                        .background(color = SLTheme.colors.gray100),
                )

                Spacer(modifier = Modifier.width(width = 8.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(space = 4.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Spacer(modifier = Modifier.height(height = 0.dp))

                    Text(
                        text = "도서",
                        style = SLTheme.typography.body2,
                        color = SLTheme.colors.gray400,
                    )

                    Text(
                        text = book.title,
                        style = SLTheme.typography.title1,
                        color = SLTheme.colors.black,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )

                    BookDetailText(
                        label = "출판사",
                        labelStyle = SLTheme.typography.body1,
                        text = book.publisher,
                        textStyle = SLTheme.typography.body3,
                        color = SLTheme.colors.gray400,
                    )

                    BookDetailText(
                        label = "저자",
                        labelStyle = SLTheme.typography.body1,
                        text = book.authors,
                        textStyle = SLTheme.typography.body3,
                        color = SLTheme.colors.gray400,
                    )

                }
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.height(108.dp)
                ) {
                    FavoriteToggleButton(
                        isSelected = book.isFavorite,
                        onFavoriteClick = onFavoriteClick,
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = book.price.toFormattedPrice(),
                        style = SLTheme.typography.title1,
                        color = SLTheme.colors.black,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BookCardPreview() {
    SLTheme {
        Column(
            modifier = Modifier.padding(all = 16.dp),
        ) {
            BookCard(
                book = BookUiModel(),
                onBookCardClick = {},
                onFavoriteClick = {},
            )
            Spacer(
                modifier = Modifier.height(height = 12.dp),
            )
            BookCard(
                book = BookUiModel(),
                onBookCardClick = {},
                onFavoriteClick = {},
            )
        }
    }
}
