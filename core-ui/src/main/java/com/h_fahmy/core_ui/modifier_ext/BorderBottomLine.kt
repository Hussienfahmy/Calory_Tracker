package com.h_fahmy.core_ui.modifier_ext

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.borderLineBottom(
    color: Color,
    strokeWidth: Dp = 1.dp
) = drawBehind {
    val stroke = strokeWidth.toPx()
    val y = size.height - stroke / 2
    drawLine(
        color = color,
        start = Offset(0f, y),
        end = Offset(size.width, y),
        strokeWidth = stroke
    )
}