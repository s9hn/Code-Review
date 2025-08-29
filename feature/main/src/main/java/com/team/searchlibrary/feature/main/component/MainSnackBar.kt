package com.team.searchlibrary.feature.main.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.searchlibrary.core.designsystem.theme.SLTheme

private const val FAVORITE_ON = "❤️ '즐겨찾기'에 저장되었어요."
private const val FAVORITE_OFF = "🤍 '즐겨찾기'에서 해제되었어요."

@Composable
internal fun MainSnackBar(isFavorite: Boolean) {
    Snackbar(
        shape = RoundedCornerShape(size = 12.dp),
        containerColor = SLTheme.colors.black,
        modifier = Modifier
            .height(height = 56.dp)
            .padding(horizontal = 10.dp),
    ) {
        Text(
            text = if (isFavorite) FAVORITE_ON else FAVORITE_OFF,
            style = SLTheme.typography.title3,
            color = SLTheme.colors.white,
        )
    }
}

@Preview
@Composable
private fun MainSnackBarPreview() {
    SLTheme {
        MainSnackBar(isFavorite = true)
    }
}
