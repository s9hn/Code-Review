package com.team.searchlibrary.feature.books_ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.searchlibrary.core.designsystem.component.singleClickable
import com.team.searchlibrary.core.designsystem.theme.SLTheme
import com.team.searchlibrary.feature.books_ui.R.drawable.ic_sort

@Composable
internal fun FilterChip(
    icon: Painter,
    iconTitle: String,
    onChipClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .heightIn(min = 40.dp)
            .clip(shape = RoundedCornerShape(size = 20.dp))
            .background(color = SLTheme.colors.white)
            .singleClickable { onChipClick() }
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = LightGray,
                ),
                shape = RoundedCornerShape(size = 20.dp),
            )
            .padding(
                horizontal = 14.dp,
                vertical = 10.dp,
            ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space = 6.dp),
        ) {
            Icon(
                painter = icon,
                contentDescription = "정렬",
                tint = SLTheme.colors.black,
                modifier = Modifier.size(size = 20.dp)
            )
            Text(
                text = iconTitle,
                style = SLTheme.typography.body5,
                color = SLTheme.colors.black,
            )
        }
    }
}

@Preview
@Composable
private fun FilterChipPreview() {
    SLTheme {
        FilterChip(
            icon = painterResource(ic_sort),
            iconTitle = "정렬",
            onChipClick = {},
        )
    }
}
