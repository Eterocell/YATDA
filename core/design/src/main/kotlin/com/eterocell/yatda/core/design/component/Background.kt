package com.eterocell.yatda.core.design.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.eterocell.yatda.core.design.theme.GradientColors
import com.eterocell.yatda.core.design.theme.LocalBackgroundTheme
import com.eterocell.yatda.core.design.theme.LocalGradientColors
import com.eterocell.yatda.core.design.theme.YATDATheme
import kotlin.math.tan

/**
 * The main background for the app.
 * Uses [LocalBackgroundTheme] to set the color and tonal elevation of a [Surface].
 *
 * @param modifier Modifier to be applied to the background.
 * @param content The background content.
 */
@Composable
fun YATDABackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val color = LocalBackgroundTheme.current.color
    val tonalElevation = LocalBackgroundTheme.current.tonalElevation
    Surface(
        color = if (color == Color.Unspecified) Color.Transparent else color,
        tonalElevation = if (tonalElevation == Dp.Unspecified) 0.dp else tonalElevation,
        modifier = modifier.fillMaxSize(),
    ) {
        CompositionLocalProvider(LocalAbsoluteTonalElevation provides 0.dp) {
            content()
        }
    }
}

/**
 * A gradient background for select screens. Uses [LocalBackgroundTheme] to set the gradient colors
 * of a [Box] within a [Surface].
 *
 * @param modifier Modifier to be applied to the background.
 * @param gradientColors The gradient colors to be rendered.
 * @param content The background content.
 */
@Composable
fun YATDAGradientBackground(
    modifier: Modifier = Modifier,
    gradientColors: GradientColors = LocalGradientColors.current,
    content: @Composable () -> Unit,
) {
    val currentTopColor by rememberUpdatedState(gradientColors.top)
    val currentBottomColor by rememberUpdatedState(gradientColors.bottom)
    Surface(
        color =
            if (gradientColors.container == Color.Unspecified) {
                Color.Transparent
            } else {
                gradientColors.container
            },
        modifier = modifier.fillMaxSize(),
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .drawWithCache {
                    // Compute the start and end coordinates such that the gradients are angled 11.06
                    // degrees off the vertical axis
                    val offset =
                        size.height *
                            tan(
                                Math
                                    .toRadians(11.06)
                                    .toFloat(),
                            )

                    val start = Offset(size.width / 2 + offset / 2, 0f)
                    val end = Offset(size.width / 2 - offset / 2, size.height)

                    // Create the top gradient that fades out after the halfway point vertically
                    val topGradient =
                        Brush.linearGradient(
                            0f to
                                if (currentTopColor == Color.Unspecified) {
                                    Color.Transparent
                                } else {
                                    currentTopColor
                                },
                            0.724f to Color.Transparent,
                            start = start,
                            end = end,
                        )
                    // Create the bottom gradient that fades in before the halfway point vertically
                    val bottomGradient =
                        Brush.linearGradient(
                            0.2552f to Color.Transparent,
                            1f to
                                if (currentBottomColor == Color.Unspecified) {
                                    Color.Transparent
                                } else {
                                    currentBottomColor
                                },
                            start = start,
                            end = end,
                        )

                    onDrawBehind {
                        // There is overlap here, so order is important
                        drawRect(topGradient)
                        drawRect(bottomGradient)
                    }
                },
        ) {
            content()
        }
    }
}

@ThemePreviews
@Composable
fun BackgroundDefault() {
    YATDATheme(disableDynamicTheming = true) {
        YATDABackground(Modifier.size(100.dp), content = {})
    }
}

@ThemePreviews
@Composable
fun BackgroundDynamic() {
    YATDATheme(disableDynamicTheming = false) {
        YATDABackground(Modifier.size(100.dp), content = {})
    }
}

@ThemePreviews
@Composable
fun BackgroundAndroid() {
    YATDATheme(androidTheme = true) {
        YATDABackground(Modifier.size(100.dp), content = {})
    }
}

@ThemePreviews
@Composable
fun GradientBackgroundDefault() {
    YATDATheme(disableDynamicTheming = true) {
        YATDAGradientBackground(Modifier.size(100.dp), content = {})
    }
}

@ThemePreviews
@Composable
fun GradientBackgroundDynamic() {
    YATDATheme(disableDynamicTheming = false) {
        YATDAGradientBackground(Modifier.size(100.dp), content = {})
    }
}

@ThemePreviews
@Composable
fun GradientBackgroundAndroid() {
    YATDATheme(androidTheme = true) {
        YATDAGradientBackground(Modifier.size(100.dp), content = {})
    }
}
