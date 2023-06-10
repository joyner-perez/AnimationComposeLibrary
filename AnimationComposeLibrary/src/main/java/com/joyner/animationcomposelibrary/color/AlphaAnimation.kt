package com.joyner.animationcomposelibrary.color

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
fun AlphaAnimation(
    defaultValuesAnimation: DefaultValuesAnimation,
    content: @Composable (alpha: Float) -> Unit
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

    content(alpha = animationExpandTo)
}