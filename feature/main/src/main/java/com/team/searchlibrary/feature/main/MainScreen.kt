package com.team.searchlibrary.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration.Short
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.team.searchlibrary.core.designsystem.theme.SLTheme
import com.team.searchlibrary.feature.main.component.MainBottomAppBar
import com.team.searchlibrary.feature.main.component.MainNavHost
import com.team.searchlibrary.feature.main.component.MainSnackBar
import kotlinx.coroutines.launch

@Composable
internal fun MainScreen(
    mainNavigator: MainNavigator = rememberMainNavigator(),
) {
    MainScreenContent(mainNavigator = mainNavigator)
}

@Composable
private fun MainScreenContent(
    mainNavigator: MainNavigator,
    modifier: Modifier = Modifier,
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var isFavorite by remember { mutableStateOf(false) }
    val showSnackBar: (isBoolean: Boolean) -> Unit = {
        isFavorite = it
        scope.launch {
            snackBarHostState.currentSnackbarData?.dismiss()
            snackBarHostState.showSnackbar("", duration = Short)
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                snackbar = {
                    MainSnackBar(isFavorite)
                },
            )
        },
        bottomBar = {
            MainBottomAppBar(
                currentTab = mainNavigator.currentTab,
                isVisible = mainNavigator.shouldShowBottomBar(),
                onTabSelected = mainNavigator::navigate,
                mainTabs = MainTab.entries,
            )
        },
        modifier = modifier
            .fillMaxSize()
            .background(color = SLTheme.colors.white)
            .windowInsetsPadding(insets = WindowInsets.safeDrawing),
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
        ) {
            MainNavHost(
                mainNavigator = mainNavigator,
                showSnackBar = showSnackBar,
            )
        }
    }
}
