package com.team.searchlibrary.feature.main.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.team.searchlibrary.core.designsystem.theme.SLTheme
import com.team.searchlibrary.feature.main.MainTab

@Composable
internal fun MainBottomAppBar(
    currentTab: MainTab?,
    isVisible: Boolean,
    mainTabs: List<MainTab>,
    onTabSelected: (mainTab: MainTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + slideIn { IntOffset(0, it.height) },
        exit = fadeOut() + slideOut { IntOffset(0, it.height) }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(height = 56.dp)
                .background(color = SLTheme.colors.white),
        ) {
            mainTabs.forEach { mainTab ->
                MainBottomBarItem(
                    mainTab = mainTab,
                    isSelected = mainTab == currentTab,
                    onItemClick = { onTabSelected(mainTab) },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainBottomAppBarPreview() {
    SLTheme {
        MainBottomAppBar(
            currentTab = MainTab.SEARCH,
            mainTabs = MainTab.entries,
            onTabSelected = {},
            isVisible = true,
        )
    }
}
