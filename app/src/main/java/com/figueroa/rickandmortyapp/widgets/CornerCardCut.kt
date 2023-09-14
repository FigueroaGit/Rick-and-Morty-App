package com.figueroa.rickandmortyapp.widgets

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class CornerCardCut(private val cornerRadius: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        return Outline.Generic(
            // Draw your custom path here
            path = drawCornerPath(size = size, cornerRadius = cornerRadius),
        )
    }

    fun drawCornerPath(size: Size, cornerRadius: Float): Path {
        return Path().apply {
            reset()
            // Top left arc
            arcTo(
                rect = Rect(
                    left = -cornerRadius,
                    top = -cornerRadius,
                    right = cornerRadius,
                    bottom = cornerRadius,
                ),
                startAngleDegrees = 90.0f,
                sweepAngleDegrees = -90.0f,
                forceMoveTo = false,
            )
            lineTo(x = size.width - cornerRadius, y = 0f)

            // Top right arc
            arcTo(
                rect = Rect(
                    left = size.width - 0F,
                    top = 0F,
                    right = size.width + 0F,
                    bottom = 0F,
                ),
                startAngleDegrees = 180.0f,
                sweepAngleDegrees = -90.0f,
                forceMoveTo = false,
            )
            lineTo(x = size.width, y = size.height)

            // Bottom right arc
            lineTo(x = size.width, y = size.height)

            // Bottom left arc
            arcTo(
                rect = Rect(
                    left = 0F,
                    top = size.height,
                    right = 0F,
                    bottom = size.height,
                ),
                startAngleDegrees = 0.0f,
                sweepAngleDegrees = -90.0f,
                forceMoveTo = false,
            )
            lineTo(x = 0f, y = size.width)
            close()
        }
    }
}
