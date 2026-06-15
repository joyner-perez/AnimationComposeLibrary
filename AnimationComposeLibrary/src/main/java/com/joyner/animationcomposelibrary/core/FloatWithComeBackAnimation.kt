package com.joyner.animationcomposelibrary.core

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
internal fun FloatWithComeBackAnimation(
    defaultValuesAnimation: DefaultValuesAnimation,
    content: @Composable (Float) -> Unit
) {
    var endAnimation by rememberSaveable { mutableStateOf(value = false) }

    val animatedValue by animateFloatAsState(
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

    content(animatedValue)
}
