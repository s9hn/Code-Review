package com.team.searchlibrary.feature.books_favorite.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.searchlibrary.core.designsystem.theme.SLTheme
import com.team.searchlibrary.feature.books_favorite.FavoriteUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FilterBottomSheet(
    uiState: FavoriteUiState,
    onTempPriceRangeChange: (priceRange: ClosedFloatingPointRange<Float>) -> Unit,
    onDismissRequest: () -> Unit,
    onFilterApplyClick: () -> Unit,
    onResetClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        containerColor = SLTheme.colors.gray50,
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(space = 16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(space = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "정상가",
                    style = SLTheme.typography.title1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = uiState.currentPriceRangeText,
                    style = SLTheme.typography.title3,
                    color = if (uiState.isPriceRangeChanged) SLTheme.colors.secondary400 else SLTheme.colors.gray500
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = uiState.fullMinPriceText,
                    style = SLTheme.typography.title3,
                )
                Text(
                    text = uiState.fullMaxPriceText,
                    style = SLTheme.typography.title3,
                )
            }

            PriceRangeSlider(
                value = uiState.sliderCurrentValue,
                onValueChange = onTempPriceRangeChange,
                valueRange = uiState.sliderValueRange,
            )

            FilterActionButtons(
                onResetClick = onResetClick,
                onApplyClick = onFilterApplyClick,
            )
        }
    }
}

@Preview(showBackground = true, heightDp = 400)
@Composable
private fun FilterBottomSheetPreview() {
    SLTheme {
        FilterBottomSheet(
            uiState = FavoriteUiState(),
            onResetClick = {},
            onDismissRequest = {},
            onFilterApplyClick = {},
            onTempPriceRangeChange = { },
        )
    }
}
