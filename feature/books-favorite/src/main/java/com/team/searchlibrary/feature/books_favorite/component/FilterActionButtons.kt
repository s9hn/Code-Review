package com.team.searchlibrary.feature.books_favorite.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.searchlibrary.core.designsystem.theme.SLTheme
import com.team.searchlibrary.feature.books_favorite.R.drawable.ic_reset

@Composable
internal fun FilterActionButtons(
    onResetClick: () -> Unit,
    onApplyClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(space = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(onClick = onResetClick)
                .padding(
                    horizontal = 20.dp,
                    vertical = 12.dp,
                )
        ) {
            Icon(
                painter = painterResource(id = ic_reset),
                contentDescription = "초기화"
            )

            Text(
                text = "초기화",
                style = SLTheme.typography.label1,
                color = SLTheme.colors.black,
            )
        }

        Button(
            onClick = onApplyClick,
            shape = RoundedCornerShape(size = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SLTheme.colors.secondary400,
            ),
            modifier = Modifier.weight(weight = 1f),
        ) {
            Text(
                text = "적용",
                style = SLTheme.typography.label1,
                color = SLTheme.colors.white,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FilterActionButtonsPreview() {
    SLTheme {
        FilterActionButtons(
            onResetClick = {},
            onApplyClick = {},
        )
    }
}
