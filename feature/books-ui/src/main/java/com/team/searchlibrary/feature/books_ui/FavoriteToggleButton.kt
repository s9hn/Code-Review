package com.team.searchlibrary.feature.books_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.searchlibrary.core.designsystem.component.singleClickable
import com.team.searchlibrary.core.designsystem.theme.SLTheme
import com.team.searchlibrary.feature.books_ui.R.drawable.ic_favorite

@Composable
fun FavoriteToggleButton(
    isSelected: Boolean,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .sizeIn(minWidth = 48.dp, minHeight = 48.dp)
            .clip(CircleShape)
            .background(SLTheme.colors.gray50)
            .singleClickable(duration = 2000L) {
                onFavoriteClick()
            }
            .padding(4.dp),
    ) {
        Icon(
            painter = painterResource(id = ic_favorite),
            contentDescription = "즐겨찾기",
            tint = if (isSelected) SLTheme.colors.primary400 else SLTheme.colors.gray400,
            modifier = Modifier.size(size = 24.dp),
        )
    }
}

@Preview
@Composable
private fun FavoriteToggleButtonPreview() {
    SLTheme {
        FavoriteToggleButton(
            isSelected = true,
            onFavoriteClick = {},
        )
    }
}