package com.team.searchlibrary.feature.books_favorite.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.team.searchlibrary.core.designsystem.theme.SLTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PriceRangeSlider(
    value: ClosedFloatingPointRange<Float>,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    modifier: Modifier = Modifier,
) {
    RangeSlider(
        value = value,
        onValueChange = onValueChange,
        valueRange = valueRange,
        startThumb = { CircleThumb() },
        endThumb = { CircleThumb() },
        track = { rangeSliderState ->
            SliderDefaults.Track(
                rangeSliderState = rangeSliderState,
                modifier = Modifier.height(height = 4.dp),
                colors = SliderDefaults.colors(
                    activeTrackColor = SLTheme.colors.secondary400,
                    inactiveTrackColor = SLTheme.colors.gray200,
                ),
                thumbTrackGapSize = 0.dp,
                drawStopIndicator = null,
            )
        },
        modifier = modifier,
    )
}

@Composable
private fun CircleThumb(modifier: Modifier = Modifier) {
    SliderDefaults.Thumb(
        interactionSource = remember { MutableInteractionSource() },
        colors = SliderDefaults.colors(thumbColor = SLTheme.colors.secondary400),
        thumbSize = DpSize(width = 16.dp, height = 16.dp),
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun CircleThumbPreview() {
    SLTheme {
        CircleThumb()
    }
}

@Preview(showBackground = true)
@Composable
private fun PriceRangeSliderPreview() {
    SLTheme {
        var value by remember { mutableStateOf(0f..1_000_000f) }

        Column(modifier = Modifier.padding(16.dp)) {
            PriceRangeSlider(
                value = value,
                onValueChange = {},
                valueRange = 0f..1_000_000f
            )
        }
    }
}
