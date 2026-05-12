package com.joyner.animationcomposelibrary.rotation

import androidx.compose.animation.core.animate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.joyner.animationcomposelibrary.core.DefaultComplexAnimation
import com.joyner.animationcomposelibrary.core.getAnimationSpec
import kotlinx.coroutines.delay

private const val FullRotationDegrees = 360f

/**
 * Axis x y rotation animation
 *
 * @param defaultValuesAnimation[DefaultComplexAnimation] required default
 *     configuration values of animation.
 * @param firstHorizontal[Boolean] optional first orientation value of
 *     animation.
 * @param content[Composable] required composable element you want to
 *     animate.
 * @author Joyner (https://github.com/joyner-perez)
 */
@Composable
fun AxisXYRotationAnimation(
    defaultValuesAnimation: DefaultComplexAnimation,
    firstHorizontal: Boolean = true,
    content: @Composable (xRotation: Float, yRotation: Float) -> Unit
) {
    var xRotation by rememberSaveable { mutableFloatStateOf(value = 0f) }
    var yRotation by rememberSaveable { mutableFloatStateOf(value = 0f) }

    if (defaultValuesAnimation.animate) {
        LaunchedEffect(key1 = Unit) {
            val stages = listOf(0f to FullRotationDegrees)
            if (defaultValuesAnimation.infinity) {
                while (true) {
                    runXYStages(stages, defaultValuesAnimation, firstHorizontal, {
                        xRotation = it
                    }, {
                        yRotation =
                            it
                    })
                    delay(defaultValuesAnimation.delayInfinityMillis.toLong())
                }
            } else {
                runXYStages(stages, defaultValuesAnimation, firstHorizontal, { xRotation = it }, {
                    yRotation =
                        it
                })
                defaultValuesAnimation.onAnimateTo(false)
                defaultValuesAnimation.onAnimationEnd()
            }
        }
    }

    content(xRotation, yRotation)
}

internal suspend fun runXYStages(
    stages: List<Pair<Float, Float>>,
    config: DefaultComplexAnimation,
    firstHorizontal: Boolean,
    setX: (Float) -> Unit,
    setY: (Float) -> Unit
) {
    for ((from, to) in stages) {
        if (firstHorizontal) {
            animate(
                initialValue = from,
                targetValue = to,
                animationSpec = getAnimationSpec(config)
            ) {
                    value,
                    _
                ->
                setY(value)
            }
            animate(
                initialValue = from,
                targetValue = to,
                animationSpec = getAnimationSpec(config)
            ) {
                    value,
                    _
                ->
                setX(value)
            }
        } else {
            animate(
                initialValue = from,
                targetValue = to,
                animationSpec = getAnimationSpec(config)
            ) {
                    value,
                    _
                ->
                setX(value)
            }
            animate(
                initialValue = from,
                targetValue = to,
                animationSpec = getAnimationSpec(config)
            ) {
                    value,
                    _
                ->
                setY(value)
            }
        }
    }
}
