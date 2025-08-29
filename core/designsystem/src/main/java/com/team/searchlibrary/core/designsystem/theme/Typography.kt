package com.team.searchlibrary.core.designsystem.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.team.searchlibrary.core.designsystem.R

data class SLTypography(
    val head1: TextStyle,   // 20sp Bold
    val title1: TextStyle,  // 18sp Bold
    val title2: TextStyle,  // 16sp Bold
    val title3: TextStyle,  // 16sp Regular
    val body1: TextStyle,   // 12sp SemiBold
    val body2: TextStyle,   // 12sp Medium
    val body3: TextStyle,   // 12sp Regular
    val body4: TextStyle,   // 14sp Regular
    val body5: TextStyle,   // 14sp SemiBold
    val label1: TextStyle,  // 14sp SemiBold
    val label2: TextStyle,  // 12sp Regular
)

private val Pretendard = FontFamily(
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
    Font(R.font.pretendard_bold, FontWeight.Bold),
)

internal val SLDefaultTypography = SLTypography(
    head1 = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, fontFamily = Pretendard),
    title1 = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, fontFamily = Pretendard),
    title2 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, fontFamily = Pretendard),
    title3 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal, fontFamily = Pretendard),
    body1 = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.SemiBold, fontFamily = Pretendard),
    body2 = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium, fontFamily = Pretendard),
    body3 = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal, fontFamily = Pretendard),
    body4 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, fontFamily = Pretendard),
    body5 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold, fontFamily = Pretendard),
    label1 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold, fontFamily = Pretendard),
    label2 = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal, fontFamily = Pretendard),
)
