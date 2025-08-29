package com.team.searchlibrary.feature.books_detail.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.searchlibrary.core.designsystem.component.NetworkImage
import com.team.searchlibrary.core.designsystem.theme.SLTheme
import com.team.searchlibrary.feature.books_ui.BookDetailText
import com.team.searchlibrary.feature.books_ui.extensions.toFormattedPrice
import com.team.searchlibrary.feature.books_ui.model.BookUiModel

@Composable
internal fun BookInformation(
    book: BookUiModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        NetworkImage(
            imageUrl = book.thumbnailUrl,
            modifier = Modifier
                .size(
                    width = 120.dp,
                    height = 180.dp,
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

        Spacer(modifier = Modifier.width(width = 20.dp))

        BookDescription(book = book)
    }
}

@Composable
private fun BookDescription(
    book: BookUiModel,
    modifier: Modifier = Modifier,
) {
    val descriptions = listOf(
        "저자" to book.authors,
        "출판사" to book.publisher,
        "출간일" to book.publishedDate,
        "ISBN" to book.isbn,
        "정상가" to book.price.toFormattedPrice(),
        "할인가" to if (book.salePrice != -1) book.salePrice.toFormattedPrice() else "",
    ).filter { it.second.isNotBlank() }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = 8.dp),
    ) {
        descriptions.forEach { (label, title) ->
            BookDetailText(
                label = label,
                labelStyle = SLTheme.typography.title2,
                text = title,
                textStyle = SLTheme.typography.title3,
                color = SLTheme.colors.black,
                maxLines = 2,
            )
        }
    }
}

@Preview
@Composable
private fun BookInformationPreview() {
    SLTheme {
        BookInformation(
            book = BookUiModel(),
        )
    }
}
