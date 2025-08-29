package com.team.searchlibrary.core.designsystem.theme

import androidx.compose.ui.graphics.Color

data class SLColorScheme(
    val transparent: Color,
    val white: Color,
    val black: Color,
    val blackAlpha10: Color,
    val blackAlpha20: Color,

    val gray50: Color,
    val gray100: Color,
    val gray150: Color,
    val gray200: Color,
    val gray400: Color,
    val gray500: Color,

    val primary400: Color,
    val secondary400: Color,
)

internal val SLLightColors = SLColorScheme(
    transparent = Color(0x00000000),
    white = Color(0xFFFFFFFF),
    black = Color(0xFF000000),
    blackAlpha10 = Color(0xFF000000).copy(alpha = 0.1f),
    blackAlpha20 = Color(0xFF000000).copy(alpha = 0.2f),

    gray50 = Color(0xFFF4F6F9),
    gray100 = Color(0xFFF4F4F4),
    gray150 = Color(0xFFE9E9E9),
    gray200 = Color(0xFFE4E4E4),
    gray400 = Color(0xFFA8A8A8),
    gray500 = Color(0xFF959595),

    primary400 = Color(0xFFE23F40),
    secondary400 = Color(0xFF4287F7),
)
