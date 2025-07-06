package com.application.themoviedb.utils

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

/**
 * @Author: Umer Dev
 * @Created: 06/07/2025
 * @File: isScrolling.kt
 */

@Composable
fun LazyGridState.isScrollingUp(): Boolean {
    var previousOffset by remember { mutableStateOf(firstVisibleItemScrollOffset) }
    val currentOffset = firstVisibleItemScrollOffset
    val scrollingUp = currentOffset < previousOffset
    previousOffset = currentOffset
    return scrollingUp
}
