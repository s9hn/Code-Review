package com.team.searchlibrary.core.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.singleClickable(
    duration: Long = 500L,
    block: () -> Unit,
): Modifier = composed {
    var lastClickTime by rememberSaveable { mutableLongStateOf(0L) }

    this.clickable {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime > duration) {
            lastClickTime = currentTime
            block()
        }
    }
}
