package com.team.searchlibrary.feature.main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.searchlibrary.core.designsystem.theme.SLTheme
import com.team.searchlibrary.feature.main.MainTab

@Composable
internal fun RowScope.MainBottomBarItem(
    mainTab: MainTab,
    isSelected: Boolean,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val contentColor =
        if (isSelected) SLTheme.colors.black else SLTheme.colors.gray400

    Column(
        modifier = modifier
            .weight(weight = 1f)
            .fillMaxHeight()
            .selectable(
                selected = isSelected,
                onClick = onItemClick,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 6.dp,
            alignment = Alignment.CenterVertically,
        ),
    ) {
        Icon(
            painter = painterResource(id = mainTab.iconResId),
            contentDescription = mainTab.title,
            tint = contentColor,
            modifier = Modifier.size(size = 20.dp),
        )
        Text(
            text = mainTab.title,
            style = SLTheme.typography.label2,
            color = contentColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainBottomBarItemPreview() {
    SLTheme {
        Row {
            MainBottomBarItem(
                mainTab = MainTab.SEARCH,
                isSelected = true,
                onItemClick = {},
            )
        }
    }
}
