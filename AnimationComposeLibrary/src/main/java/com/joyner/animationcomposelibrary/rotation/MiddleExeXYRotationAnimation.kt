package com.joyner.animationcomposelibrary.rotation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.joyner.animationcomposelibrary.core.DefaultComplexAnimation
import kotlinx.coroutines.delay

private const val HalfRotationDegrees = 180f
private const val FullRotationDegrees = 360f

/**
 * Middle exe xy rotation animation
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
fun MiddleExeXYRotationAnimation(
    defaultValuesAnimation: DefaultComplexAnimation,
    firstHorizontal: Boolean = true,
    content: @Composable (xRotation: Float, yRotation: Float) -> Unit
) {
    var xRotation by rememberSaveable { mutableFloatStateOf(value = 0f) }
    var yRotation by rememberSaveable { mutableFloatStateOf(value = 0f) }

    if (defaultValuesAnimation.animate) {
        LaunchedEffect(key1 = Unit) {
            val stages =
                listOf(
                    0f to HalfRotationDegrees,
                    HalfRotationDegrees to FullRotationDegrees
                )
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
