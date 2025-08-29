package com.team.searchlibrary.feature.books_ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.team.searchlibrary.core.designsystem.theme.SLTheme

@Composable
fun BookDetailText(
    label: String,
    labelStyle: TextStyle,
    text: String,
    textStyle: TextStyle,
    color: Color,
    maxLines: Int = 1,
    modifier: Modifier = Modifier,
) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = labelStyle.copy(
                    color = SLTheme.colors.black,
                ).toSpanStyle()
            ) {
                append("$label ")
            }
            append(text)
        },
        style = textStyle,
        color = color,
        maxLines = maxLines,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun BookDetailTextPreview() {
    SLTheme {
        BookDetailText(
            label = "출판사",
            labelStyle = SLTheme.typography.body1,
            text = "여기어때",
            textStyle = SLTheme.typography.body3,
            color = SLTheme.colors.gray400,
        )
    }
}