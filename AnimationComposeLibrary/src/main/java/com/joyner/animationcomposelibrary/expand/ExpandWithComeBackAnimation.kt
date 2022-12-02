package com.joyner.animationcomposelibrary.expand

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.joyner.animationcomposelibrary.core.DefaultValuesAnimation
import com.joyner.animationcomposelibrary.core.getAnimationSpec

/**
 * Expand with come back animation
 *
 * @param defaultValuesAnimation[DefaultValuesAnimation] required default
 *     configuration values of animation.
 * @param content[Composable] required composable element you want to
 *     animate.
 * @author Joyner (https://github.com/joyner-perez)
 */
@Composable
fun ExpandWithComeBackAnimation(
    defaultValuesAnimation: DefaultValuesAnimation,
    content: @Composable (size: Float) -> Unit
) {
    var endAnimation by rememberSaveable { mutableStateOf(false) }

    val animationExpandTo by animateFloatAsState(
        targetValue = if (defaultValuesAnimation.animate) {
            defaultValuesAnimation.targetValue
        } else {
            defaultValuesAnimation.initValue
        },
        animationSpec = getAnimationSpec(
            defaultValuesAnimation = defaultValuesAnimation,
            durationInMillis = defaultValuesAnimation.durationInMillis / 2,
            delayInitInMillis = if (endAnimation && defaultValuesAnimation.infinity) {
                defaultValuesAnimation.delayInfinityMillis
            } else {
                0
            }
        ),
        finishedListener = {
            endAnimation = it == defaultValuesAnimation.initValue
            if (defaultValuesAnimation.infinity) {
                defaultValuesAnimation.onAnimateTo(defaultValuesAnimation.animate.not())
            } else if (defaultValuesAnimation.animate) {
                defaultValuesAnimation.onAnimateTo(defaultValuesAnimation.animate.not())
            }
            if (defaultValuesAnimation.infinity.not() && endAnimation) {
                defaultValuesAnimation.onAnimationEnd()
            }
        }
    )

    content(size = animationExpandTo)
}