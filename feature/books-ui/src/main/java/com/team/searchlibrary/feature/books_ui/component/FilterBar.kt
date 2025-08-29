package com.team.searchlibrary.feature.books_ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.searchlibrary.core.designsystem.theme.SLTheme
import com.team.searchlibrary.feature.books_ui.R.drawable.ic_filter
import com.team.searchlibrary.feature.books_ui.R.drawable.ic_sort

@Composable
internal fun FilterBar(
    text: String,
    isFilterChipVisible: Boolean,
    onFilterClick: () -> Unit,
    onSortClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = text,
            style = SLTheme.typography.body5,
            color = SLTheme.colors.black,
        )

        Spacer(modifier = Modifier.weight(weight = 1f))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space = 6.dp),
        ) {
            if (isFilterChipVisible) {
                FilterChip(
                    icon = painterResource(id = ic_filter),
                    iconTitle = "필터",
                    onChipClick = onFilterClick,
                )
            }

            FilterChip(
                icon = painterResource(id = ic_sort),
                iconTitle = "정렬",
                onChipClick = onSortClick,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FilterBarPreview() {
    SLTheme {
        FilterBar(
            text = "오름차순",
            onSortClick = {},
            onFilterClick = {},
            isFilterChipVisible = true,
        )
    }
}
