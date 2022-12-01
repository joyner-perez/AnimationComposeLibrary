package com.joyner.animationcomposelibrary.expand

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.joyner.animationcomposelibrary.core.DefaultValuesAnimation

/**
 * Expand with come back animation
 *
 * @param defaultValuesAnimation[DefaultValuesAnimation] required default
 *     configuration values of animation.
 * @param onExpandTo[() -> Unit] required callback when animation is
 *     expanded.
 * @param content[Composable] required composable element you want to
 *     animate.
 * @author Joyner (https://github.com/joyner-perez)
 */
@Composable
fun ExpandWithComeBackAnimation(
    defaultValuesAnimation: DefaultValuesAnimation,
    onExpandTo: (Boolean) -> Unit,
    content: @Composable (size: Float) -> Unit
) {
    var endAnimation by rememberSaveable { mutableStateOf(false) }

    val animationExpandTo by animateFloatAsState(
        targetValue = if (defaultValuesAnimation.animate) {
            defaultValuesAnimation.targetValue
        } else {
            defaultValuesAnimation.initValue
        },
        animationSpec = tween(
            durationMillis = defaultValuesAnimation.durationInMillis / 2,
            delayMillis = if (endAnimation && defaultValuesAnimation.infinity) {
                defaultValuesAnimation.delayInfinityMillis
            } else {
                0
            },
            easing = defaultValuesAnimation.easingValue
        ),
        finishedListener = {
            endAnimation = it == defaultValuesAnimation.initValue
            if (defaultValuesAnimation.infinity) {
                onExpandTo(defaultValuesAnimation.animate.not())
            } else if (defaultValuesAnimation.animate) {
                onExpandTo(defaultValuesAnimation.animate.not())
            }
        }
    )

    content(size = animationExpandTo)
}