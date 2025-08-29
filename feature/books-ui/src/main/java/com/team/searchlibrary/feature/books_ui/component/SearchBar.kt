package com.team.searchlibrary.feature.books_ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.searchlibrary.core.designsystem.theme.SLTheme
import com.team.searchlibrary.feature.books_ui.R.drawable.ic_search

@Composable
internal fun SearchBar(
    text: String,
    onTextChange: (input: String) -> Unit,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    val keyboard = LocalSoftwareKeyboardController.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .heightIn(min = 48.dp)
            .clip(shape = RoundedCornerShape(size = 24.dp))
            .background(color = SLTheme.colors.gray100)
            .padding(
                horizontal = 16.dp,
                vertical = 10.dp,
            ),
    ) {
        Icon(
            painter = painterResource(id = ic_search),
            contentDescription = "검색",
            tint = SLTheme.colors.gray500,
            modifier = Modifier.size(size = 20.dp)
        )

        Spacer(modifier = Modifier.width(width = 12.dp))

        BasicTextField(
            value = text,
            onValueChange = onTextChange,
            singleLine = true,
            textStyle = SLTheme.typography.title3.copy(
                color = SLTheme.colors.black,
            ),
            cursorBrush = SolidColor(value = SLTheme.colors.black),
            modifier = Modifier.weight(weight = 1f),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    onSearchClick()
                    focusManager.clearFocus()
                    keyboard?.hide()
                }
            ),
            decorationBox = { inner ->
                Box {
                    if (text.isEmpty()) {
                        Text(
                            text = "제목 또는 저자를 입력하세요.",
                            style = SLTheme.typography.title3,
                            color = SLTheme.colors.gray500,
                            maxLines = 1,
                        )
                    }
                }
                inner()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchBarPreview() {
    SLTheme {
        SearchBar(
            text = "",
            onTextChange = {},
            onSearchClick = {},
        )
    }
}
