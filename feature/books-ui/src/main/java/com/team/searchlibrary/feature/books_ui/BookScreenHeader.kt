package com.team.searchlibrary.feature.books_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.searchlibrary.core.designsystem.theme.SLTheme
import com.team.searchlibrary.feature.books_ui.component.FilterBar
import com.team.searchlibrary.feature.books_ui.component.SearchBar

@Composable
fun BookScreenHeader(
    text: String,
    headerTitle: String,
    sortTitle: String,
    isFilterChipVisible: Boolean,
    onTextChange: (input: String) -> Unit,
    onSearchClick: () -> Unit,
    onSortClick: () -> Unit,
    modifier: Modifier = Modifier,
    onFilterClick: () -> Unit = {},
) {
    Box {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .background(color = SLTheme.colors.white)
                .padding(horizontal = 20.dp),
        ) {
            Spacer(modifier = Modifier.height(height = 20.dp))

            Text(
                text = headerTitle,
                style = SLTheme.typography.title2,
                color = SLTheme.colors.black,
            )

            Spacer(modifier = Modifier.height(height = 28.dp))

            SearchBar(
                text = text,
                onTextChange = onTextChange,
                onSearchClick = onSearchClick,
            )
            Spacer(modifier = Modifier.height(height = 12.dp))

            FilterBar(
                text = sortTitle,
                onFilterClick = onFilterClick,
                onSortClick = onSortClick,
                isFilterChipVisible = isFilterChipVisible,
            )

            Spacer(modifier = Modifier.height(height = 12.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BooksHeaderPreview() {
    SLTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            BookScreenHeader(
                text = "text",
                headerTitle = "검색",
                sortTitle = "오름차순",
                isFilterChipVisible = true,
                onSearchClick = {},
                onTextChange = {},
                onSortClick = { },
            )
        }
    }
}
