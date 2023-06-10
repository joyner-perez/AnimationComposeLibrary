package com.joyner.animationcomposelibrary.color

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.joyner.animationcomposelibrary.core.DefaultValuesAnimation
import com.joyner.animationcomposelibrary.core.getAnimationSpec

/**
 * Move with come back animation
 *
 * @param defaultValuesAnimation[DefaultValuesAnimation] required default
 *     configuration values of animation.
 * @param content[Composable] required composable element you want to
 *     animate.
 * @author Joyner (https://github.com/joyner-perez)
 */
@Composable
fun AlphaWithComeBackAnimation(
    defaultValuesAnimation: DefaultValuesAnimation,
    content: @Composable (alpha: Float) -> Unit
) {
    var endAnimation by rememberSaveable { mutableStateOf(false) }

    val animationMoveTo by animateFloatAsState(
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

    content(alpha = animationMoveTo)
}