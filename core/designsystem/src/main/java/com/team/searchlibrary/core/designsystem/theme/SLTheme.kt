package com.team.searchlibrary.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

private val LocalColors = staticCompositionLocalOf { SLLightColors }
private val LocalTypography = staticCompositionLocalOf { SLDefaultTypography }

object SLTheme {
    val colors: SLColorScheme
        @Composable get() = LocalColors.current
    val typography: SLTypography
        @Composable get() = LocalTypography.current
}

@Composable
fun SLTheme(
    colors: SLColorScheme = SLLightColors,
    typography: SLTypography = SLDefaultTypography,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides typography
    ) {
        MaterialTheme(content = content)
    }
}
