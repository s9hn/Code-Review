package com.team.searchlibrary.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion.Fit
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.team.searchlibrary.core.designsystem.R

@Composable
fun NetworkImage(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = Fit,
    alignment: Alignment = Alignment.Center,
    @DrawableRes placeholderRes: Int = R.drawable.base_image,
) {
    if (LocalInspectionMode.current) {
        Image(
            painter = painterResource(id = placeholderRes),
            contentDescription = contentDescription,
            contentScale = contentScale,
            alignment = alignment,
            modifier = modifier,
        )
        return
    }

    val context = LocalContext.current
    val imageRequest = remember(imageUrl, context) {
        ImageRequest.Builder(context)
            .data(imageUrl)
            .crossfade(true)
            .build()
    }

    SubcomposeAsyncImage(
        model = imageRequest,
        contentDescription = contentDescription,
        contentScale = contentScale,
        alignment = alignment,
        modifier = modifier,
        loading = {
            Box(
                modifier = Modifier.matchParentSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(Modifier.size(size = 32.dp))
            }
        },
        error = {
            Image(
                painter = painterResource(id = placeholderRes),
                contentDescription = contentDescription,
                contentScale = contentScale,
                alignment = alignment,
                modifier = Modifier.matchParentSize(),
            )
        },
    )
}

@Preview
@Composable
private fun UrlImagePreview() {
    NetworkImage(
        imageUrl = "",
        modifier = Modifier.size(100.dp),
    )
}
