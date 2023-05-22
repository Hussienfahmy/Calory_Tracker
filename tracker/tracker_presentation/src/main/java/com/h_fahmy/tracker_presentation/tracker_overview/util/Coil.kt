package com.h_fahmy.tracker_presentation.tracker_overview.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest

/**
 * extension function to remember an [AsyncImagePainter] with [ImageRequest.Builder] as parameter for accessing builder properties.
 */
@Composable
inline fun rememberAsyncImagePainter(
    data: Any?,
    builder: ImageRequest.Builder.() -> Unit
): AsyncImagePainter =
    coil.compose.rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data).apply(builder).build()
    )