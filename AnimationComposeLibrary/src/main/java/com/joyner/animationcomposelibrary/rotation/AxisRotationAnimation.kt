package com.joyner.animationcomposelibrary.rotation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.joyner.animationcomposelibrary.core.DefaultValuesAnimation

/**
 * Axis rotation animation
 *
 * @param defaultValuesAnimation[DefaultValuesAnimation] required default
 *     configuration values of animation.
 * @param content[Composable] required composable element you want to
 *     animate.
 * @author Joyner (https://github.com/joyner-perez)
 */
@Composable
fun AxisRotationAnimation(
    defaultValuesAnimation: DefaultValuesAnimation,
    content: @Composable (rotationDegree: Float) -> Unit
) {
    var endAnimation by rememberSaveable { mutableStateOf(false) }

    val animationRotationDegreeTo by animateFloatAsState(
        targetValue = if (defaultValuesAnimation.animate) {
            defaultValuesAnimation.targetValue
        } else {
            defaultValuesAnimation.initValue
        },
        animationSpec = tween(
            durationMillis = defaultValuesAnimation.durationInMillis,
            delayMillis = if (endAnimation && defaultValuesAnimation.infinity) {
                defaultValuesAnimation.delayInfinityMillis
            } else {
                defaultValuesAnimation.delayInitInMillis
            },
            easing = defaultValuesAnimation.easingValue
        ),
        finishedListener = {
            endAnimation = it == defaultValuesAnimation.targetValue
            if (defaultValuesAnimation.infinity) {
                defaultValuesAnimation.onAnimateTo(defaultValuesAnimation.animate.not())
            }
        }
    )

    content(rotationDegree = animationRotationDegreeTo)
}