package com.team.searchlibrary.feature.books_detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.searchlibrary.core.designsystem.component.singleClickable
import com.team.searchlibrary.feature.books_detail.R
import com.team.searchlibrary.feature.books_ui.FavoriteToggleButton

@Composable
internal fun BookDetailTopBar(
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
    ) {
        Box(
            modifier = Modifier
                .singleClickable { onBackClick() }
                .padding(top = 12.dp, bottom = 12.dp, end = 24.dp),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "뒤로가기",
                modifier = Modifier.size(size = 24.dp)
            )
        }

        FavoriteToggleButton(
            isSelected = isFavorite,
            onFavoriteClick = onFavoriteClick,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BookDetailTopBarPreview() {
    BookDetailTopBar(
        isFavorite = true,
        onFavoriteClick = {},
        onBackClick = {},
    )
}
