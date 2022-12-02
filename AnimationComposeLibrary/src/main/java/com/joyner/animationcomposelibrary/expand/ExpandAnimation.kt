package com.joyner.animationcomposelibrary.expand

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.joyner.animationcomposelibrary.core.DefaultValuesAnimation
import com.joyner.animationcomposelibrary.core.getAnimationSpec

/**
 * Expand animation
 *
 * @param defaultValuesAnimation[DefaultValuesAnimation] required default
 *     configuration values of animation.
 * @param content[Composable] required composable element you want to
 *     animate.
 * @author Joyner (https://github.com/joyner-perez)
 */
@Composable
fun ExpandAnimation(
    defaultValuesAnimation: DefaultValuesAnimation,
    content: @Composable (size: Float) -> Unit
) {
    val animationExpandTo by animateFloatAsState(
        targetValue = if (defaultValuesAnimation.animate) {
            defaultValuesAnimation.targetValue
        } else {
            defaultValuesAnimation.initValue
        },
        animationSpec = getAnimationSpec(
            defaultValuesAnimation = defaultValuesAnimation
        ),
        finishedListener = {
            defaultValuesAnimation.onAnimationEnd()
        }
    )

    content(size = animationExpandTo)
}